(ns fr.grunwald.async-ex-test.doo-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [async-error.core-test]))

(doo-tests 'fr.grunwald.async-ex-test)
