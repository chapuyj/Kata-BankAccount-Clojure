(ns bank-account-kata-clojure.core
  (:gen-class))

; private

(defn- add-operation [account amount date]
  (let [operation {:amount amount :date date}]
    (conj account operation)))

; public

(defn create-account [] [])

(defn deposit [account amount date]
  (add-operation account amount date))

(defn withdraw [account amount date] 
  (add-operation account (- amount) date))

(defn balance [account]
  (reduce #(+ %1 (:amount %2)) 0 account))

; main

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))