# Saferoom

## Få saferoom-pluginnet ind på din server ved at gøre følgende:

1. Sæt .jar filen ind i din plugins mappe.
2. Download WorldGuard og sæt det ind i din plugins mappe (hvis du har WorldGuard, ignorer dette)
3. Genstart din server

Du kan altid tilføje flere saferooms i configgen - samt ændre tiden og teleport-out kordinaterne. Vælg hvilke grupper saferoom skal integrere med i configgen.

```
actionbar-tekst: "&bTid: &3{time}s" ##{time} er tiden i saferoomet
saferoom:
  tid: 120 ##skal angives i sekunder
  1:
    region-name: "saferoom-1"
    teleport-out: "world;100;63;100;0.0;0.0"
  2:
    region-name: "saferoom-2"
    teleport-out: "world;100;63;100;0.0;0.0"
  3:
    region-name: "saferoom-3"
    teleport-out: "world;100;63;100;0.0;0.0"
  4:
    region-name: "saferoom-4"
    teleport-out: "world;100;63;100;0.0;0.0"

vagt-groups:
  - "vagt"
  - "officer"
  - "inspektør"
  - "direktør"
