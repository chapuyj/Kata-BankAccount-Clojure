(ns bank-account-kata-clojure.core
  (:gen-class))

; utils

(defn long-str [& strings] (clojure.string/join "\n" strings))

(defn- compute-balance [operations]
  (reduce #(+ %1 (:amount %2)) 0 operations))

(defn add-balance-to-operation [operation previous-statements]
  (let [previous-balance (compute-balance previous-statements)
        balance (+ previous-balance (:amount operation))]
    (merge {:balance balance} operation)))

(defn add-balance-to-operations [operations]
  (reduce
    #(conj %1 (add-balance-to-operation %2 %1))
    []
    operations))

(defn- add-operation [account amount date]
  (let [operation {:amount amount :date date}]
    (conj account operation)))

(defn- statements-as-string [statement]
  (format "%s | %d | %d" (:date statement) (:amount statement) (:balance statement)))

; public

(defn create-account [] [])

(defn deposit [account amount date]
  (add-operation account amount date))

(defn withdraw [account amount date] 
  (add-operation account (- amount) date))

(defn balance [account]
  (compute-balance account))

(defn print-statements [account]
  (->> (add-balance-to-operations account)
       (reverse)
       (map statements-as-string)
       (clojure.string/join "\n")
       (str "Date | Operation | Balance\n")))

; main

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))