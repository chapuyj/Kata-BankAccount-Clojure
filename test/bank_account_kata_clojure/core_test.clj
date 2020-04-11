(ns bank-account-kata-clojure.core-test
  (:require [clojure.test :refer :all]
            [bank-account-kata-clojure.core :refer :all]))

(deftest account-test

  ; create

  (testing "should create account"
    (is (not (nil? (create-account)))))

  ; operations

  (testing "should make a deposit"
    (is (= [{:amount 50 :date "01/02/2020"}]
           (deposit (create-account) 50 "01/02/2020"))))

  (testing "should make two deposits"
    (is (= [{:amount 100 :date "01/02/2020"}
            {:amount 20 :date "02/02/2020"}] 
            (-> (create-account)
                (deposit 100 "01/02/2020")
                (deposit 20 "02/02/2020")))))

  (testing "should make a withdraw"
    (is (= [{:amount -70 :date "05/02/2020"}] 
            (withdraw (create-account) 70 "05/02/2020"))))

  (testing "should make two withdraws"
    (is (= [{:amount -40 :date "11/02/2020"}
            {:amount -60 :date "12/02/2020"}]  
            (-> (create-account)
                (withdraw 40 "11/02/2020")
                (withdraw 60 "12/02/2020")))))

  (testing "should make two deposits and two withdraws"
    (is (= [{:amount 100 :date "21/02/2020"}
            {:amount -20 :date "22/02/2020"}
            {:amount 10 :date "23/02/2020"}
            {:amount -30 :date "24/02/2020"}] 
            (-> (create-account)
                (deposit 100 "21/02/2020")
                (withdraw 20 "22/02/2020")
                (deposit 10 "23/02/2020")
                (withdraw 30 "24/02/2020")))))

  ; balance

  (testing "should compute balance after a deposit"
    (is (= 75 
           (balance (deposit (create-account) 75 "01/02/2020")))))

  (testing "should compute balance after two deposits"
    (is (= 250 
           (-> (create-account)
               (deposit 100 "01/04/2020")
               (deposit 150 "02/04/2020")
               (balance)))))

  (testing "should compute balance after a withdraw"
    (is (= -80
           (balance (withdraw (create-account) 80 "05/02/2020")))))

  (testing "should compute balance after two withdraw"
    (is (= -100 
           (-> (create-account)
               (withdraw 70 "01/04/2020")
               (withdraw 30 "02/04/2020")
               (balance)))))

  (testing "should compute balance after two deposits and two withdraws"
    (is (= 20
           (-> (create-account)
               (deposit 20 "01/04/2020")
               (deposit 30 "01/04/2020")
               (withdraw 10 "01/04/2020")
               (withdraw 20 "02/04/2020")
               (balance)))))
)