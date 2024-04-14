# Murali Krishnan Dheekshitha - Project Portfolio Page

## Project: BudgetBuddy
BudgetBuddy is a product for users who wish to handle and track any current/future expenses on a singular platform.
BudgetBuddy provides a faster and more efficient way to track and calculate expenses and provides the ability to deal
with finances on a singular platform with ease. It has multiple financial tracking options to suit your needs.

## Summary of Contributions
My primary contributions include developing the budget setting functions, and enabling users to delete their expenses
or reduce their savings, thereby improving the app's usability and user experience.

### New Feature: Added the ability to set and manage budget (Pull Request, [#66](https://github.com/AY2324S2-CS2113-T12-3/tp/pull/66), [#91](https://github.com/AY2324S2-CS2113-T12-3/tp/pull/117))
- **What it does:** Allows users to set and adjust budgets for various categories such as groceries, transport, and
  housing. Users can define a maximum spending limit for each category, which helps in monitoring and controlling
  their expenditures.
- **Justification:** This feature is pivotal for users aiming to adhere to their financial goals, and ensure that they
  do not overspend in any category
- **Highlights:** Implementing this feature requires a deep understanding of the app's different functions and how they
  all work together. One challenge of implementing this feature was integrating existing functions like `Expenses` so that
  I am able to retrieve the relevant expense information to check if the user is still within their budget.

### New Feature: Added the ability to delete expenses (Pull Request [#25](https://github.com/AY2324S2-CS2113-T12-3/tp/pull/25), [#38](https://github.com/AY2324S2-CS2113-T12-3/tp/pull/38))
- **What it does:** Allows users to delete expenses that have been added wrongly or are outdated
- **Justification:** This feature improves the app's flexibility by allowing users to correct their mistakes, ensuring
  that their financial records remain accurate

### New Feature: Added the ability to reduce savings (Pull Request [#25](https://github.com/AY2324S2-CS2113-T12-3/tp/pull/25), [#39](https://github.com/AY2324S2-CS2113-T12-3/tp/pull/39))
- **What it does:** Enables users to record when they withdraw or spend from their savings, updating savings accordingly
- **Justification:** Essential for maintaining an accurate record of savings, especially when savings are used to cover 
unexpected expenses or large purchases

## Code contributed: [RepoSense Link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=Dheekshitha2&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

## Enhancements to existing features
- Added an alert when user tries to add an expense which would exceed the budget set (Pull Request [#195](https://github.com/AY2324S2-CS2113-T12-3/tp/pull/195))
- Integrated my budget function into currency converter (Pull request [#197](https://github.com/AY2324S2-CS2113-T12-3/tp/pull/197))
- Changed from reducing savings by index, to reducing savings by category instead (Pull request [#217](https://github.com/AY2324S2-CS2113-T12-3/tp/pull/217))
- Integrated saving and loading for my budget functions (Pull request [#217](https://github.com/AY2324S2-CS2113-T12-3/tp/pull/217))

## Documentation

### Developer's Guide
- Added documentation for `reduce savings` and `delete expenses` (Pull Request [#216](https://github.com/AY2324S2-CS2113-T12-3/tp/pull/216))
- Added sequence diagram for `ReduceSavings`, `ListBudget` and `SetBudget` (Pull Request [#220](https://github.com/AY2324S2-CS2113-T12-3/tp/pull/220))
- Added use cases for my functions (Pull Request [#220](https://github.com/AY2324S2-CS2113-T12-3/tp/pull/220))
- Added documentation for `set budget` and `print budget` (Pull Request [#69](https://github.com/AY2324S2-CS2113-T12-3/tp/pull/69), [#216](https://github.com/AY2324S2-CS2113-T12-3/tp/pull/216))

### User Guide
- Added implementation for `reduce savings` and `delete expenses`
- Added implementation for all the budget functions (Pull Request [#130](https://github.com/AY2324S2-CS2113-T12-3/tp/pull/130))