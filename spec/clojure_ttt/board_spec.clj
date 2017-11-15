(ns clojure-ttt.board-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.board :refer :all]))

(defn- vec-for-string [board-string] (clojure.string/split board-string #""))

(describe "make-board"
          (it "has 9 empty strings"
              (should= 
                [" " " " " "
                 " " " " " "
                 " " " " " "]
                (make-board))))

(describe "play-on-board"
          (it "plays on board"
              (should=
                [" " " " " "
                 "X" " " " "
                 " " " " " "]
                (play-on-board (make-board) 4 "X"))))

(describe "available-moves"
          (it "gets available moves"
              (should=
                [2, 3, 8, 9]
                (available-moves (vec-for-string "X  OOXX  ")))))

(describe "winner"
          (it "is true for X"
              (should= true (winner (vec-for-string "XXXOO    ") "X")))

          (it "is false for X"
              (should= false (winner (vec-for-string "XX OOOX  ") "X")))
          
          (it "is true for O"
              (should= true (winner (vec-for-string "XOX O XO ") "O")))
          
          (it "is true for X"
              (should= true (winner (vec-for-string "XO  XO  X") "X")))
          
          )

