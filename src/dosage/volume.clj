(ns dosage.volume)

(defn- compute-part [volume level]
  "Computes the part corresponding to the given dosage level."
  (let [occurrences (int (Math/floor (/ volume level)))]
    {:level       level
     :occurrences occurrences
     :remainder   (- volume (* occurrences level))}))

(defn- smaller-part
  "Builds a part smaller by one occurrence compared to the one provided as input."
  [part]
  (if (zero? (:occurrences part))
    nil
    {:level       (:level part)
     :occurrences (dec (:occurrences part))
     :remainder   (+ (:remainder part) (:level part))}))

(declare partition)

(defn- backtrack
  [part levels]
  (cond
    (nil? part) nil
    (zero? (:remainder part)) (list part)
    :else (let [acc (partition (:remainder part) levels)]
            (if (nil? acc)
              (backtrack (smaller-part part) levels)
              (if (zero? (:occurrences part))
                acc                                         ; filter out part
                (conj acc part))))))

(defn partition
  "Partitions a given volume by the available levels."
  [volume levels]
  (if (empty? levels)
    nil
    (let [part (compute-part volume (first levels))]
      (backtrack part (rest levels)))))
