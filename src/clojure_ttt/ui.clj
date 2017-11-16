(ns clojure-ttt.ui
  (:require [clojure-ttt.board :refer :all]))

(defn output [message] (println message))

(defn- inner-string [index, value]
  (cond
    (not= " " value) value
    :else (str (inc index))))

(defn- cell-string [index, value] 
  (cond
    (= index 8) (inner-string index value)
    (= 2 (mod index 3)) (str (inner-string index value) "\n-----\n")
    :else (str (inner-string index value) "|")))

(defn- cell-strings [board]
  (map-indexed #(cell-string %1, %2) board))

(defn- board-str [board]
  (clojure.string/join
    (cell-strings board)))

(defn output-board [board]
  (output (board-str board)))

(defn output-game-result [board]
  (cond
    (winner? board "X") (output "X won.")
    (winner? board "O") (output "O won.")
    (tie? board) (output "The game was a tie.")))

(defn input
  ([prompt]
   (println prompt)
   (read-line))
  ([]
   (read-line)))

(defn- parseable-as-int? [string]
  (try
    (Integer/parseInt string)
    true
    (catch Exception e false)))

(defn input-integer []
  (let [s (input)]
    (cond
      (parseable-as-int? s) (Integer/parseInt s)
      :else (recur))))

(defn- move-available? [board, move]
  (->> board
       (available-moves)
       (some (partial = move))
       (boolean)))

(defn input-move [board]
  (let [s (input)]
    (cond

      (parseable-as-int? s)
      (cond
        (move-available? board (Integer/parseInt s))
        (Integer/parseInt s)

        :else
        (recur board))

      :else
      (recur board))))
