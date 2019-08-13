import math
def number_fragments (messageSize_bytes, overheadPerPacket_bytes, maximumNPacketSize_bytes):
    s = messageSize_bytes
    o = overheadPerPacket_bytes
    m = maximumNPacketSize_bytes
    return  math.ceil (s / (m - o))

print(number_fragments(10000,20,1500))


def last_fragment_size (messageSize_bytes, overheadPerPacket_bytes, maximumNPacketSize_bytes):
    s = messageSize_bytes
    o = overheadPerPacket_bytes
    m = maximumNPacketSize_bytes
    n = number_fragments(s,o,m) - 1
    return messageSize_bytes - n * (m-o) + o

print (last_fragment_size(10000, 20, 1500))