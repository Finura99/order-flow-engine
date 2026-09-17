# Order Flow Engine

## Design Notes: Order Model

The first object in this system is the "Order" since the entire system will be depending on the receiving and processing of trading orders.

An Order needs a side such as a BUY and SELL, and a status, such as RECIEVED, VALIDATING, PROCESSED, FILLED or REJECTED.

At start, i have used enum for OrderSide and OrderStatus instead of plain strings because enum provides restricted allowed values and makes the code secure and easier to maintain/understand.

Main.java is used only as a simple entry point to create and test the objects and the moving parts of the system  before adding Springboot or multithreading.

This is similar to validation in my Python/FastAPI projects, but Java gives me stricter type safety earlier.

14/09/2026 - journal: From what i've learnt in python, although solid principles isnt always the answer, its higly applicable here for loose coupling of the system and will easily grow if we keep things seperate yet still connected...

Each class has a job:

1. Order = This is the trading ticket and status changing methods
2. OrderSide = This is the vocabulary "BUY" & "SELL". Using allowed strict values so we avoid using weak strings like "buy" "BYY"
3. OrderStatus = Als vocabulary but for the lifecycle of the order
4. OrderValidator = This checks business rules
5. OrderProcessor = Uses the validator. Flow: recieve order -> ask validator -> if invalid -> reject order -> return failed result. if valid -> validate -> process -> fill -> return success result
6. ProcessingResult = This is the reciept for the flow. Order is the thing being processed, this is about the outcome after processing, like fastapi but we do it manually here.
7. For now, main is jsut the demo runner, Orchestrates and wires everything together
