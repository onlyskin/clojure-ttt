(ns clojure-ttt.computer-player
  (:require [clojure-ttt.ui :refer :all])
  (:require [clojure-ttt.board :refer :all]) )

(defn- winning-score [] 1000)
(defn- losing-score [] -1000)

(defn- score [board]
  (cond
    (tie? board) 0
    (winner? board (current-marker board)) (winning-score)
    :else (losing-score)))

(defn- available-nodes [board]
  (->> board
       (available-moves)
       (map (partial hash-map :score nil :move))))

(defn- greater-by-score [a b]
  (cond
    (> (:score a) (:score b)) a
       :else b))

(defn max-by-score [nodes]
  (reduce #(greater-by-score %1 %2) nodes))

(defn index-of-max [array]
  (->> array
       (map-indexed #(vector %))
       (apply max-key second)
       (first)))

(defn minimax [depth board]
  (cond

    (game-over? board)
    (score board)

    :else
    (let [nodes (->> board
                     (available-moves)
                     (map (partial play-on-board board))
                     (map (partial minimax (inc depth))))]
      (cond (= 0 depth) (nth
                          (available-moves board)
                          (first
                            (apply
                              max-key
                              second
                              (map-indexed vector nodes))))
            :else (max-by-score nodes)))
    ))

(defn get-minimax-move [board]
  (minimax 0 board))
