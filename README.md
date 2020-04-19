# Kata - Bank Account - Clojure

## Versions

- [computed-balance](https://github.com/chapuyj/kata_bank-account_clojure/tree/master/computed-balance) : the balance is computed each time it is needed.
- [stored-balance](https://github.com/chapuyj/kata_bank-account_clojure/tree/master/stored-balance) : the balance is stored with each operation.

## Usage

Made with [leiningen](https://leiningen.org/).

#### Run

`lein run`

#### Test

`lein test`

## Notes

### General notes

#### reduce
- `%1` is the **accumulated result**
- `%2` is the **current value** of the iterated collection 

#### #()
Syntax for anonym functions.

#### ->
Chaining function calls.
Result passed in first parameter to the next function.
[See documentation](https://clojuredocs.org/clojure.core/-%3E)

#### ->>
Chaining function calls.
Result passed in last parameter to the next function.
[See documentation](https://clojuredocs.org/clojure.core/-%3E%3E)

### Computed-Balance notes

#### compute-balance
Was first done directly in `balance` function. 
After writing `add-balance-to-operation`, I wanted to use this function inside.
Clojure can not find `balance` function because the declaration of `balance` is done after `add-balance-to-operation`.
So I created one more function `compute-balance` declarated before both other functions.

#### Unit tests and TDD
I did the whole kata in TDD, by writting acceptance tests for account first. 
I was lost when I tried to write `print-statements` function. Learning a language, mixing multiple function calls, trying to reduce, map, compute balance, etc. So I started more specific unit tests to help me and to decompose in multiple functions.

## Quick links

- [Clojure Getting started](https://clojure.org/guides/getting_started)
- [Clojure core docs](https://clojuredocs.org/core-library)
- [Clojure cheatsheet](https://clojure.org/api/cheatsheet)

