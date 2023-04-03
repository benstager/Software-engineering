                                BEN STAGER'S FINANCIAL SIMULATOR 2021
                                        (co. Stager, 2021)
                                    
This financial simulator is derived as an exercise to determine discrepancies in yearly wealth between those who are 'financially' literate - and those who are not ('nfl'). Being financially illiterate can entail but is not limited to - putting all of your money into one account with a fixed interest rate, and paying a small down payment on your house and inheriting a large mortgage from the bank. The goal, after using this 'simulator' is to show that being financially literate can significantly improve and increase the amount of wealth you can accumulate. This user manual will go through the general struct created, and the member functions to describe how the algorithm works.



"struct financial Identity"

This struct contains several member variables, listed and described below:

double debt; --- used to initialize the amount of debt each person takes in, in this case it is 52000
double mortgage; --- the mortgage is the priceOfHouse (210000) minus the down payment
double savings; --- each person is initialized with 5000 in savings
double checking; --- each person starts with nothing in their checking account
double priceOfHouse; --- the price of each person's house is 210000
double downPayment; --- downPayment is 20% of priceOfHouse for fl, 5% of priceOfHouse for nfl
double add1Pay; --- this is the amount that is added to when paying debt, 100 for fl, 1 for nfl
int yearsWithDebt; --- incremented within debt everytime a debt is payed
int yearsRented; --- incremented within rent everytime apartment is rented
int debtPaid; --- total amount of debt that is paid
bool ownsHouse; --- equates to 'true' if house is bought, else it is 'false'



"void savingsPlacement(struct financialIdentity *person, double interestRate);"

The savingsPlacement function is simple. Each time it is called, the persons savings compounds at a yearly rate of either 7% for fl, or 1% for nfl. This is added to existing savings each time it is called.



"void debt(struct financialIdentity *person, double interestRate, double add1pay)"

The debt function is used to pay off the person's existing debt: 52000 for both people. The person pays a monthly payment at a 3% interest rate on their existing debt. The fl person pays it by adding 100 dollars to the payment, the nfl person pays it by adding 1 dollar to the payment. The monthly payment is subtracted from the exisitng debt. If the person does not have enough in their checking to pay it, the program pays what they can, sets the checking to 0, then pays off the remainder from their savings account. The loop runs for 12 months (1 year), each time it's called.



"void rent(struct financialIdentity *person, double rentAmount)"

The rent function is used to pay a certain rent per month amount (950 for both). Every time it is called, it is payed for 1 year at a time. The same reasoning is used in debt with payment, being that the rent is payed if enough is in checking. If not checking is set to 0, the amount in checking is payed, then the rest is payed using savings.



"void house(struct financialIdentity *person, double mortgageAmount, double interestRate, int mortgageTerm)"

The house function is used to pay off the mortgage on a house. The mortgage for fl is 210000 - (.2*21000). The mortgage of course retains its own interest rate. 4.5% is the interest rate for fl, 6% is the rate for nfl. The mortgage is payed for 30 years, and it is payed by month so a for loop for 12 months is implemented. Monthly interest, discount factor, and monthly payment are all used implementing their classical formulas. As was for the last two functions, the house is payed if enough is in checking, if not savings is used.



"int* simulate(struct financialIdentity *person, double yearlySalary, bool financiallyLiterate)"

The function simulate is the heart of the simulator. It is of type int *, meaning that an array is returned. The function loops for 40 years, adding the amount of wealth after each year to the i'th index of the array. It begins by putting 20% of the person's salary into their checking, and 30% of the person's salary into their savings. The function then splits into two main branches - if the person is financial literate, or if the person is not. If they are, debt and savingsPlacement are called using fl's parameters, if not, they are called using nfl's parameters. Then, each branch splits into two sub branches - if they have enough in their savings to buy 20% of a house for fl, and if they have enough of their savings to buy 5% of a house for nfl. If not, the rent function is called until they are able to do so. At the end, the i'th index of the array is their savings plus their checking, and subtracts their debt added to their mortgage.



"void printWealth(int *personArray)"

This function simply acts as a unit test to print the wealth to the screen.



"int main()"

The main function is implemented to finally declare fl and nfl as pointers, and memory is allocted for them. Each initial attribute is declared for each. Then, a file is declared to write each index of the array to a file.
