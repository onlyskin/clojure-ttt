(ns clojure-ttt.board)

(defn make-board [] (vec (repeat 9 " ")))

(defn play-on-board
  [board, position, marker]
  (assoc board (- position 1) marker))

(declare empty-cell)
(defn available-moves [board]
  (map #(+ 1 (get % 0)) (filter #(empty-cell %) (map-indexed #(vec [%1, %2]) board))))

(defn- empty-cell [cell]
  (= " " (get cell 1)))
