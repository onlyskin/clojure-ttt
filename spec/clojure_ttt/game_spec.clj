(ns clojure-ttt.game-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.game :refer :all])) 

(describe "run game"
          (it "output prints empty board"
              (should-contain
                #"(?s)1.*2.*3.*4.*5.*6.*7.*8.*9"
                (with-out-str
                  (with-in-str "1\n4\n2\n5\n3" (run-game)))))

          (it "output prints X"
              (should-contain
                "X"
                (with-out-str
                  (with-in-str "1\n4\n2\n5\n3" (run-game)))))

          (it "output prints O"
              (should-contain
                "O"
                (with-out-str
                  (with-in-str "1\n4\n2\n5\n3" (run-game)))))

          (it "ends" (should true))

          (xit "output print X won"
              (should-contain
                "X won"
                (with-out-str
                  (with-in-str "1\n4\n2\n5\n3" (run-game)))))
          )
