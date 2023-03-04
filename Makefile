# Darius-Florentin Neatu <neatudarius@gmail.com>

# Exemplu de Makefile pentru tema

# tag-uri obligatorii (nume + comportament identic)
# build    => compileaza toata tema
#             (ATENTIE! E important - NU compilati in tag-urile de run. Sesizati?)
# run-p$ID => ruleaza problema cu ID-ul specificat (1, 2, 3, 4)
# clean    => sterge toate fisierele generate

# restul este la alegerea studentului
# TODO

# nume necesar (build)
build:
	javac Walsh.java Statistics.java Prinel.java

run-p1:      # nume necesar
	java Walsh

run-p2:      # nume necesar
	java Statistics
run-p3:      # nume necesar
	java Prinel
run-p4:      # nume necesar

clean:		 # nume necesar
	rm -rf *.class
