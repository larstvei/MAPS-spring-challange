;; Assume S is a string that is infinite in size in both directions. The
;; string consists solely of whitespace, except for at one index which is
;; inhabited by the character 'x'. The string develops according to the
;; following rules, which are applied every minute:
;;
;; * If a whitespace character has at least one neighbour character 'x' at
;;   minute i, it becomes inhabited by an 'x' at minute i+1.
;;
;; * If an 'x' has one or less neighbour characters 'x' at minute i, it
;;   continues to live at minute i+1.
;;
;; * If an 'x' has two neighbour characters 'x' at minute i, it dies and
;;   becomes a whitespace at minute i+1.
;;
;; For example, assume we have the substring "___x___" (where whitespace is
;; denoted with underscore). After one rule application we get "__xxx__",
;; and after two rule applications we get "_xx_xx_". So after two minutes
;; there are four 'x' characters in the string.
;;
;; How many 'x' characters are there in the string after 253 minutes?

(ns maps-spring-challenge.cellular-automata)

(defn apply-rule [[x y z :all string]]
  (if (= y \space)
    (if (or  (= \x x) (= \x z)) \x \space)
    (if (and (= \x x) (= \x z)) \space \x)))

(loop [string "   x   " i 0]
  (if (< i 253)
    (let [strs (partition 3 1 string)]
      (recur (concat "  " (map apply-rule strs) "  ") (inc i)))
    (count (filter #(= % \x) string))))
