(ns clojure-ttt.menu-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.game :refer :all]   
            [clojure-ttt.human-player :refer :all]   
            [clojure-ttt.computer-player :refer :all]   
            [clojure-ttt.ui :refer :all]   
            [clojure-ttt.menu :refer :all])) 

(describe "menu"
          (with-stubs)

          (it "calls get-choice with [[0 1]]"
              (with-redefs
                [get-choice (stub :get-choice {:return "2"})]
                (main-menu)

                (should-have-invoked :get-choice {:with [["1" "2"]]})))

          (it "calls exit when choice is 2"
              (with-redefs
                [exit (stub :exit)
                 get-choice (stub :get-choice {:return "2"})]
                (main-menu)

                (should-have-invoked :exit)))

         (it "calls make-game when choice is 1"
             (with-redefs
               [make-game (stub :make-game)
                get-choice (stub :get-choice {:return "1"}) ]
               (main-menu)

               (should-have-invoked :make-game))))


(describe "make game"
          (with-stubs)

          (it "calls run-game with human move funcs when input is human human"
              (with-redefs
                [run-game (stub :run-game)]
                (with-out-str
                  (with-in-str "h\nh\n" (make-game)))

                (should-have-invoked
                  :run-game
                  {:with [[get-human-move get-human-move]]})))

          (it "calls run-game with human move and negamax move when input is human computer"
              (with-redefs
                [run-game (stub :run-game)]
                (with-out-str
                  (with-in-str "h\nc\n" (make-game)))

                (should-have-invoked
                  :run-game
                  {:with [[get-human-move get-negamax-move]]})))

          (it "calls run-game with negamax move and human move when input is computer human"
              (with-redefs
                [run-game (stub :run-game)]
                (with-out-str
                  (with-in-str "c\nh\n" (make-game)))

                (should-have-invoked
                  :run-game
                  {:with [[get-negamax-move get-human-move]]}))))
