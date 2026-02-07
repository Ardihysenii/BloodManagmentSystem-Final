PROJECT: BLOODMANAGMENTSYSTEM

Ky projekt eshte shume me teper se thjesht rreshta kodi ne Java apo tabela te ftohta ne SQL; per
ne, eshte nje perpjekje per me kriju nje sistem qe ne fund te dites mundet me shpetu jete. Ideja
lindi nga nevoja per me pas nje komunikim me te shpejte mes spitaleve dhe dhuruesve. Ne fillim,
te them te drejten, jemi perplasur shume me erroret e kuqe ne IntelliJ. Kemi pas momente kur
databaza nuk na "bindej" ose kur programi mbyllej vetem pse shtypnim nje shkronje ne vend te nje
numri. Por, pikerisht ketu e kemi bere ndryshimin: kemi shtuar blloke "try-catch" ne cdo qoshe qe
programi te jete "human" dhe te mos humbe durimin me perdoruesin, por t'i jape nje mesazh
sqarues ne vend qe te crash-et.
Pjesa per te cilen jemi me se shumti krenare eshte logjika e kerkesave per gjak. Ne nuk kemi
dashur qe sistemi t'i bezdise te gjithe dhuruesit me njoftime te panevojshme. Kur nje spital ben nje
kerkese (permes asaj metodes createRequest), sistemi yne eshte "i zgjuar". Ai shikon grupin e
gjakut qe kerkohet dhe njoftimin ua dergon vetem atyre perdoruesve qe e kane pikerisht ate grup
gjaku. Pra, nese nje spital ka nevoje emergjente per A+, vetem dhuruesit e grupit A+ do ta shohin
kete kerkese ne listen e tyre. Kjo e ben sistemin shume efikas dhe shume njerzor, sepse nuk i
"spam-on" njerezit, por i thirr vetem ata qe mund te ndihmojne vertet ne ate moment. Kjo lidhje
realizohet permes nje query te forte ne SQL qe filtron te dhenat ne kohe reale, duke u siguruar qe
kerkesa e spitalit te gjeje dhuruesin e duhur pa humbur asnje sekonde.
Sa i perket teknologjise, ne e kemi lidhur "trupin" e programit ne Java me "memorien" e tij ne SQL
Server permes JDBC-se. Kemi krijuar nje klas hibride te quajtur DatabaseConnection qe sherben
si nje lloj ure e sigurt. Per te mos e mbingarkuar serverin, kemi perdorur ate qe quhet 
"try-withresources", qe ne gjuhen e thjeshte do te thote se programi yne eshte shume i rregullt: e hap deren
e databazes, kryen punen dhe e mbyll ate menjehere pasi mbaron, qe te mos mbetet asnje
"mbeturine" ne memorie. E kemi organizuar kodin ne paketa si DAO dhe Service, qe nese neser
duam te shtojme diçka te re, te mos e prishim gjithe ate qe kemi ndertuar me mund deri tash.
Eshte nje projekt i ndertuar me zemer, duke mesuar nga gabimet dhe duke u perpjekur me e bere
procesin e dhurimit te gjakut aq te thjeshte sa nje klikim butoni.
