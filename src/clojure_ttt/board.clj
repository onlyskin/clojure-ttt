(ns clojure-ttt.board)

(defn make-board [] (vec (repeat 9 " ")))

(defn- empty-cell? [cell]
  (= " " (get cell 1)))

(defn available-moves [board]
  (->> board
       (map-indexed #(vec [%1, %2]))
       (filter #(empty-cell? %))
       (map #(+ 1 (get % 0)))))

(defn- markers [] ["O" "X"])

(defn- current-marker [board]
  (as-> board v
    (available-moves v)
    (count v)
    (mod v 2)
    (get (markers) v)))

(defn play-on-board [board, position]
  (assoc board (- position 1) (current-marker board)))

(defn- win-in-path? [board marker path]
  (every? #(= % marker) (map #(get board %) path)))

(defn- rows []
  (let [n 3]
    (->> (range n)
         (map #(vec [(+ 0 (* n %)) (+ 1 (* n %)) (+ 2 (* n %))]))
         (vec))))

(defn- columns [] (apply mapv vector (rows)))

(defn- diagonals [] [[0 4 8] [6 4 2]])

(defn- win-paths [] (concat (rows) (columns) (diagonals)))

(defn winner? [board marker]
   (boolean
     (some #(win-in-path? board marker %) (win-paths))))

(defn- marker-1 [] (get (markers) 1))

(defn- marker-0 [] (get (markers) 0))

(defn winner [board]
  (cond
    (= true (winner? board (marker-1))) (marker-1)
    (= true (winner? board (marker-0))) (marker-0)))

(defn- full? [board]
  (not-any? #(= " " %) board))

(defn tie? [board]
  (and
    (full? board)
    (not (winner? board (marker-1)))
    (not (winner? board (marker-0)))))

(defn game-over? [board]
  (or
    (winner? board (marker-1))
    (winner? board (marker-0))
    (tie? board)))
