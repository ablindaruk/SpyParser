# SpyParser
Parser for OLX rental real estate.
SpyParser (by default) parse from OLX real estate rental pages, from such categories as flats, rooms, houses, garages and commerce in Ukraine and flats, houses and commerce abroad. Default links are specified in PagesOlx file. 
Parser goes to each category link specified in PagesOlx file and comes through all pages in each category to obtain all the notes available on each page. 
After parsing completion, all notes will be written into new_notes.txt file in the resources folder inside your project.  
Then parser will compare notes from new_notes.txt with notes in old_notes.txt (old_notes.txt file contains notes which where found on OLX earlier, while previous run).
The difference in notes among new_notes.txt and old_notes.txt files will be written into differ_notes.txt file, in resources folder. In the above line inside differ_notes.txt file you can see number of notes bellow.
After it all notes founded while current run of SpyParser will be written into old_notes.txt file and will be ready for next run of the SpyParser. 
Please note that after completion of current run of SpyParser, both files - old_notes.txt and new_notes.txt will contain the same notes received while current run. And differ_notes.txt will contain the notes which differ from previous run of SpyParser.
Test.java folder contains three files with simple Junit tests for methods in package classes.
Dependencies you may find in pom.xml file.
