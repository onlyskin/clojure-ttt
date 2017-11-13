(ns clojure-ttt.board-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.board :refer :all]))

(describe "cells"
  (it "has cells"
    (should= 
      [" " " " " "
       " " " " " "
       " " " " " "]
      (make-board))))

(describe "play on board"
  (it "plays on board"
    (should=
      [" " " " " "
       "X" " " " "
       " " " " " "]
      (play-on-board (make-board) 4 "X"))))
