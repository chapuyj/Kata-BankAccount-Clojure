(ns bank-account-kata-clojure.core
  (:gen-class))

; utils

(defn long-str [& strings] (clojure.string/join "\n" strings))

(defn last-balance-or-zero [account]
  (if-let [last-balance (:balance (peek account))] last-balance 0))

(defn- add-operation [account amount date]
  (let [balance (+ (last-balance-or-zero account) amount)
        operation {:amount amount :balance balance :date date}]
    (conj account operation)))

(defn- operation-as-string [operation]
  (format "%s | %d | %d" (:date operation) (:amount operation) (:balance operation)))

; public

(defn create-account [] [])

(defn deposit [account amount date]
  (add-operation account amount date))

(defn withdraw [account amount date]
  (add-operation account (- amount) date))

(defn balance [account]
  (last-balance-or-zero account))

(defn print-statements [account]
  (->> (map operation-as-string account)
       (reverse)
       (clojure.string/join "\n")
       (str "Date | Operation | Balance\n")))

; main

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
