

//
//  financialIdentity.c
//  financialLiteracy.c
//
//  Created by Ben Stager on 11/30/21.
//
#include <stdio.h>
#include <stdlib.h>
#include "financialIdentity.h"
#include <math.h>
#include <stdbool.h>

// MORE IN DEPTH EXPLANATIONS OF FUNCTIONS AND ALGORITHMS EXIST IN "README.MD"

// The function compounds the persons savings at a rate at 1 + interestRate and adds it
// to initial savings
void savingsPlacement(struct financialIdentity *person, double interestRate){
    person->savings = person->savings *(interestRate+1);
}

// The function runs a monthly debt payment, which is payed at a rate of (.3 * debt) + add1pay
// The function lets the user pay with their checking account, if not they pay what they can
// from their checking, its set to 0, then they pay the remainder from their savings
void debt(struct financialIdentity *person, double interestRate, double add1pay){
    for (int i = 0; i < 12; i++){
        double monthlyDebt = (person->debt * .03) + add1pay;
            if (person->checking >= monthlyDebt){
                person->checking -= monthlyDebt;
                person->debt -= monthlyDebt;
                person->debtPaid += monthlyDebt;
            }else{
                person->savings -= monthlyDebt;
                person->debt -= monthlyDebt;
                person->debtPaid += monthlyDebt;
            }
        }
    int debtRemaining = person->debt * (1+interestRate);
    person->debt = debtRemaining;
    person->yearsWithDebt++;
    }

// The function subtracts the rent amount from either the checking or savings, using the same
// logical thinking as debt
void rent(struct financialIdentity *person, double rentAmount){
    double rentAmt = rentAmount * 12;
    double remainder = rentAmt - person->checking;
    
    if (person->checking >= rentAmount){
        person->checking -= rentAmt;
    }else{
        person->checking = 0;
        person->savings -= remainder;
    }
    person->yearsRented += 1;
}

// This function is only called when the person owns a house, and implements the
// various formulas described in the pdf. It loops for 12 months, and additionally
// implements an if statement that will compound the person's mortgage until it is payed off
void house(struct financialIdentity *person, double mortgageAmount, double interestRate, int mortgageTerm){
    
    mortgageAmount = person->mortgage;
    int payments = mortgageTerm * 12;
    double monthlyInterest = (interestRate)/12;
    double discountFactor = (pow((1+monthlyInterest),payments)-1)/(interestRate)*(pow(1+interestRate, payments));
    double monthlyPayment = mortgageAmount/discountFactor;
    
    double remainder = monthlyPayment - person->checking;
    for (int i = 0; i < 12; i++){
        if (person->checking >= monthlyPayment){
            person->checking -= monthlyPayment;
            person->mortgage -= monthlyPayment;
        }else{
            person->checking = 0;
            person->savings -= remainder;
            person->mortgage -= remainder;
        }
        
        if (person->mortgage > 0){
            person->mortgage += (person->mortgage * interestRate);
        }
    }
}

// The simulate function returns an array of size 40. The array is composed of wealth for each year.
// The function, split into fl and nfl branches, further splits into two branches whether they
// have enough money in their accounts to pay the down payment on the house. If they
// do not have enough, rent is called. When they do have enough, the mortgage is updated and
// ownsHouse returns to true
int* simulate(struct financialIdentity *person, double yearlySalary, bool financiallyLiterate){
    int* wealth = (int*)malloc(41*sizeof(int));
    double checkingRate = .2;
    double savingsRate = .3;
    wealth[0] = -52500;
    
    for (int i = 1; i < 40; i++){
        person->checking += yearlySalary*checkingRate;
        person->savings += yearlySalary*savingsRate;
        
        //FL branch
        if (financiallyLiterate == true){
            savingsPlacement(person, .07);
            debt(person, .2, person->add1Pay);
            if (person->savings >= person->downPayment){
                person->ownsHouse = true;
                person->mortgage = person->priceOfHouse  - person->downPayment;
                house(person, person->mortgage, .045, 30);
            }else{
                person->ownsHouse = false;
                rent(person, 950);
            }
        //NFL BRANCH
        }else{
            savingsPlacement(person, .01);
            debt(person, .2, person->add1Pay);
            if (person->savings >= person->downPayment){
                person->ownsHouse = true;
                person->mortgage = person->priceOfHouse - person->downPayment;
                house(person, person->mortgage, .06, 30);
            }else{
                person->ownsHouse = false;
                rent(person, 950);
            }
            
        }
        wealth[i] = (person->checking + person->savings) - (person->debt + person->mortgage);
    }
    return wealth;
}

// A unit test is devised to print the array
void printWealth(int *personArray){
    for (int i = 0; i < 40; i++){
        printf("Wealth after %d year: %d",i+1, personArray[i]);
        printf("\n");
    }
}

// In the main functions the structs are initialized as pointers
// The results of each array are printed to both the screen and a file titled 'output.txt'
int main(){
    struct financialIdentity *fl;
    struct financialIdentity *nfl;
    fl = (struct financialIdentity*)malloc(sizeof(struct financialIdentity));
    nfl = (struct financialIdentity*)malloc(sizeof(struct financialIdentity));
    
    //FL initial values
    
    fl->debt = 52000;
    fl->mortgage = 168000;
    fl->savings = 5000;
    fl->checking = 0;
    fl->priceOfHouse = 210000;
    fl->downPayment = 42000;
    fl->add1Pay = 100;
    fl->yearsWithDebt = 0;
    fl->yearsRented = 0;
    fl->yearsWithDebt = 0;
    fl->ownsHouse = 0;
    
    //NFL initial values
    
    nfl->debt = 52000;
    nfl->mortgage = 199500;
    nfl->savings = 5000;
    nfl->checking = 0;
    nfl->priceOfHouse = 210000;
    nfl->downPayment = 10500;
    nfl->add1Pay = 100;
    nfl->yearsWithDebt = 0;
    nfl->yearsRented = 0;
    nfl->yearsWithDebt = 0;
    nfl->ownsHouse = 0;
    
    int *array1;
    int *array2;
    array1 = (int*)malloc(41*(sizeof(int)));
    array2 = (int*)malloc(41*(sizeof(int)));
    array1 = simulate(fl, 69000, true);
    array2 = simulate(nfl, 69000, false);
    
    printWealth(array1);
    printf("\n");
    printWealth(array2);
    
    FILE* output;
    
    output = fopen("output.txt", "w");
    
    fprintf(output, "Ben's Financial Simulator: ");
    fprintf(output, "\n");
    
    for (int i = 0; i < 40; i++){
        fprintf(output, "Wealth for FL after %d year: $%d", i, array1[i]);
        fprintf(output, "\n");
    }
    
    for (int i = 0; i < 40; i++){
        fprintf(output, "Wealth for NFL after %d year: $%d", i, array2[i]);
        fprintf(output, "\n");
    }
    
    
    
}
