(ns bank-account-kata-clojure.core-test
  (:require [clojure.test :refer :all]
            [bank-account-kata-clojure.core :refer :all]))

(deftest account-test

  ; create

  (testing "should create account"
    (is (not (nil? (create-account)))))

  ; operations

  (testing "should make a deposit"
    (is (= [{:amount 50 :balance 50 :date "19/04/2020"}]
           (deposit (create-account) 50 "19/04/2020"))))

  (testing "should make two deposits"
    (is (= [{:amount 100 :balance 100 :date "19/04/2020"}
            {:amount 20 :balance 120 :date "20/04/2020"}] 
            (-> (create-account)
                (deposit 100 "19/04/2020")
                (deposit 20 "20/04/2020")))))

  (testing "should make a withdraw"
    (is (= [{:amount -70 :balance -70 :date "19/04/2020"}] 
            (withdraw (create-account) 70 "19/04/2020"))))

  (testing "should make two withdraws"
    (is (= [{:amount -40 :balance -40 :date "19/04/2020"}
            {:amount -60 :balance -100 :date "20/04/2020"}]  
            (-> (create-account)
                (withdraw 40 "19/04/2020")
                (withdraw 60 "20/04/2020")))))

  (testing "should make two deposits and two withdraws"
    (is (= [{:amount 100 :balance 100 :date "19/04/2020"}
            {:amount -20 :balance 80 :date "20/04/2020"}
            {:amount 10 :balance 90 :date "21/04/2020"}
            {:amount -30 :balance 60 :date "22/04/2020"}] 
            (-> (create-account)
                (deposit 100 "19/04/2020")
                (withdraw 20 "20/04/2020")
                (deposit 10 "21/04/2020")
                (withdraw 30 "22/04/2020")))))

  ; balance

  (testing "should get balance after two deposits and two withdraws"
    (is (= 20
           (-> (create-account)
               (deposit 20 "19/04/2020")
               (deposit 30 "20/04/2020")
               (withdraw 10 "21/04/2020")
               (withdraw 20 "22/04/2020")
               (balance)))))

  ; print

  (testing "should print header when there is no operation"
    (is (= "Date | Operation | Balance\n"
           (print-statements (create-account)))))

  (testing "should print statements after one deposit"
    (is (= (long-str "Date | Operation | Balance"
                     "19/04/2020 | 75 | 75")
           (print-statements (deposit (create-account) 75 "19/04/2020")))))

  (testing "should print statements after one deposit and one withdraw"
    (is (= (long-str "Date | Operation | Balance"
                     "20/04/2020 | -5 | 5"
                     "19/04/2020 | 10 | 10")
            (-> (create-account)
                (deposit 10 "19/04/2020")
                (withdraw 5 "20/04/2020")
                (print-statements)))))
)

(deftest last-balance-or-zero-test

  (testing "should return 0 if empty"
    (is (= 0
           (last-balance-or-zero []))))
          
  (testing "should return last balance with one item"
    (is (= 100
           (last-balance-or-zero [{:balance 100}]))))

  (testing "should return last balance with two items"
    (is (= 120
           (last-balance-or-zero [{:balance 100}, {:balance 120}]))))
)