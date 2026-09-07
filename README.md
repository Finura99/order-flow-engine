# Order FLow Execution

## Design Notes: Order Model

The first object in this system is the "Order" since the entire system will be depending on the receiving and processing of trading orders.

An Order needs a side such as a BUY and SELL, and a status, such as RECIEVED, VALIDATING, PROCESSED, FILLED or REJECTED.

At start, i have used enum for OrderSide and OrderStatus instead of plain strings because enum provides restricted allowed values and makes the code secure and easier to maintain/understand.

Main.java is used only as a simple entry point to create and test the objects and the moving parts of the system  before adding Springboot or multithreading.

This is similar to validation in my Python/FastAPI projects, but Java gives me stricter type safety earlier.
