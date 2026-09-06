# Temperature and humidity analyzer

This is a funny app letting play with can-bus protocol, dbc scheme and can-bus encoding and decoding.

Things I've made:

1. Developed own dbc scheme for can-bus protocol optimizing it for real-existing temperature sensor DS18B20
2. Learned how to encode and decode data via can-bus protocol
3. Written fake arduino generator logs for my own dbc scheme :D
4. Created java temperature and humidity analyzers each working for their own can ids (OxAA - for temperature, 0xAB -
   for humidity)
5. Written few tests for letting everything be working fine

PS. dbc scheme for this can-bus protocol can be found via resources folder

PPS. Tests can be extended I've just been too bored writing all of them for 100% coverage so I wrote them on every bug
I've found to let application be fully workable.

PPPS. Any ideas are greatly appreciated.