# BookStore-App

## Summary 

A simple client-side book store application compiled in NetBeans for recording and retrieving customer and book information through a GUI.

## Core Classes & Purposes

### BookStoreApp
- Acts as the main application controller.

- Maintains references to:

  - currentState: The active state of the application.

  - currentBooks: The book inventory.

  - currentCustomers: The list of users.

- Key Methods:

  - setState(AppState): Switches the app's state.

  - setScreen(): Likely updates the UI or view according to the state.

### AppState (Interface)
- Interface for all possible application states.

- Declares:

  - pressButton()

  - Exit()

### State Implementations
Each state represents a different screen or mode of the app, implementing the AppState interface.

- LoginState

- OwnerStartState

- CustomersState

- BooksState

- CustomerStartState

- PurchaseState

  Most of these also implement:

  - Logout() (not in LoginState)

  - Exit()

  - pressButton() (main interaction handler)

### Books
- Attributes: name, price

- Methods:

  - addBook(name, price)

  - deleteBook()

### Customers
- Attributes: name, password, points, status

- Methods:

  - addCustomer(name, password)

  - deleteCustomer()
