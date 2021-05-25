Aplikacja REST:

Lista zdarzeń sportowych(/getEvents):
* kliknięcie na element listy powoduje przekierowanie do VideoPlayera, w którym prezentowany jest fimik z URL
* data jest prezentowana rosnąco , informacja o dacie powinny się zmieniac w zależności od tego czy data jest dzisiejsza("Today") lub wczorajsza("Yesterday")
* aplikacja zapamiętuję pozycje scrollowania listy(np po zmianie orientacji urządzenia na horyzontalną)

Lista harmonogramu zdarzeń(/getSchedule):
* lista prezentuję tylko wydarzenia z datą dzisiejszą
* odświeżanie listy odbywa się automatycznie co 30 sekund
* tak jak powyżej zapamiętywana jest pozycja scrollowania

Ponadto obie listy mają załączone zdjęcia pobierane z URL, odnoszące się do każdego elementu listu

![Screenshot](https://i.postimg.cc/13qdJRN9/img1.jpg=250x250) ![Screenshot](https://i.postimg.cc/CKCQpvNX/img3.jpg=250x250)

