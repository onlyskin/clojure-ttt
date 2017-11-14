(ns clojure-ttt.board-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.board :refer :all]))

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
                (available-moves
                  ["X", " ", " ", "O", "O", "X", "X", " ", " "]))))
