## This is shopping cart with  pricing rules
Original requirements can be found here 
https://github.com/DiUS/coding-tests/blob/master/dius_shopping.md

- This solution uses decorator pattern where it applies the price rules to items added into shopping cart.
- It uses separate class for different pricing rules calculation which provides flexibility to add more rules without breaking existing logics. 
  
Assumptions notes
- It assumes that one has to scan all items in order to get added them into order line items of the cart. Current logic doesn't add free items.
- It currently doesn't support same products to be added into more than one pricing rule

How to run test
- Import the projects with Junit and Java 8 or higher
- Execute the CheckoutTest as it covers all scenarios. 
- There are unit testing for different types of pricing rules
