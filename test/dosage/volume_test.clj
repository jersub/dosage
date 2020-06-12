(ns dosage.volume-test
  (:require [clojure.test :refer :all]
            [dosage.volume :as volume]))

(deftest partition-test
  (testing "leaf"
    (is (nil? (volume/partition 1000 []))))
  (testing "success with a single level"
    (is (= [{:level       100
             :occurrences 10
             :remainder   0}]
           (volume/partition 1000 [100]))))
  (testing "fails with a single level"
    (is (nil? (volume/partition 50 [100]))))
  (testing "success with several levels"
    (is (= [{:level       100
             :occurrences 6
             :remainder   50}
            {:level       50
             :occurrences 1
             :remainder   0}]
           (volume/partition 650 [100 50]))))
  (testing "success with several levels while skipping an intermediary one"
    (is (= [{:level       100
             :occurrences 6
             :remainder   50}
            {:level       50
             :occurrences 1
             :remainder   0}]
           (volume/partition 650 [100 75 50]))))
  (testing "success with backtracking"
    (is (= [{:level       125
             :occurrences 4
             :remainder   150}
            {:level       100
             :occurrences 1
             :remainder   50}
            {:level       50
             :occurrences 1
             :remainder   0}]
           (volume/partition 650 [125 100 50])))
    (is (= [{:level       100
             :occurrences 1
             :remainder   50}
            {:level       50
             :occurrences 1
             :remainder   0}]
           (volume/partition 150 [125 100 50])))))
