(ns clojure-ttt.board)

(defn make-board [] (vec (repeat 9 " ")))

(defn play-on-board
  [board, position, marker]
  (assoc board (- position 1) marker))

(declare empty-cell)
(defn available-moves [board]
  (map #(+ 1 (get % 0)) (filter #(empty-cell %) (map-indexed #(vec [%1, %2]) board))))

(declare win-paths)
(declare win-in-path)
(defn winner [board marker]
  (boolean (some #(win-in-path board marker %) (win-paths)))
  )

(defn- empty-cell [cell]
  (= " " (get cell 1)))

(defn- rows []
  (vec
    (map
      #(vec [(+ 0 (* 3 %)) (+ 1 (* 3 %)) (+ 2 (* 3 %))])
      (range 3))))

(defn- columns [] (apply mapv vector (rows)))

(defn- diagonals [] [[0 4 8] [6 4 2]])

(defn- win-paths [] (concat (rows) (columns) (diagonals)))

(defn- win-in-path [board marker path]
  (every? #(= % marker) (map #(get board %) path)))

