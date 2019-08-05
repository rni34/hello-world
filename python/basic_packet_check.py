def composepacket (version, hdrlen, tosdscp, totallength, identification, flags, fragmentoffset, timetolive, protocoltype, headerchecksum, sourceaddress, destinationaddress):
    if version != 4:
        return 1
    elif hdrlen < 0 or len(bin(hdrlen)) >  6:
        return 2
    elif tosdscp < 0 or len(bin(tosdscp)) > 8:
        return 3
    elif totallength < 0 or len(bin(totallength)) > 18:
        return 4
    elif identification < 0 or len(bin(identification)) > 18:
        return 5
    elif flags < 0 or len(bin(flags)) > 5:
        return 6
    elif fragmentoffset < 0 or len(bin(fragmentoffset)) > 15:
        return 7
    elif timetolive < 0 or len(bin(timetolive)) > 10:
        return 8
    elif protocoltype < 0 or len(bin(protocoltype)) > 10:
        return 9
    elif headerchecksum < 0 or len(bin(headerchecksum)) > 18:
        return 10
    elif sourceaddress < 0 or len(bin(sourceaddress)) > 34:
        return 11
    elif destinationaddress < 0 or len(bin(destinationaddress)) > 34:
        return 12
    else:
        first_byte = version << 4 | hdrlen
        list1 = [first_byte,tosdscp,totallength,identification,flags,fragmentoffset,timetolive,protocoltype,headerchecksum,sourceaddress,destinationaddress]
        res = bytearray()
        for element in list1:
            if element <= 255:
                x = element.to_bytes(1,byteorder='big')
                res += x
            elif element <= 65535:
                x = element.to_bytes(2,byteorder='big')
                res += x
            elif element <= 16777215:
                x = element.to_bytes(3,byteorder='big')
                res += x
            elif element <= 4294967295:
                x = element.to_bytes(4,byteorder='big')
                res += x
        return res

print(composepacket(4,5,0,1500,24200,0,63,22,6,4711, 2190815565, 3232270145))


def basicpacketcheck (pkt):
    print(len(pkt))
    first_byte = pkt[0]
    if (first_byte >> 4) & 0xf != 4:
        return 2
    return (pkt[10] << 4) | pkt[11]



pkt1 = bytearray ([0x45, 0x0, 0x0, 0x1e, 0x4, 0xd2, 0x0, 0x0, 0x40, 0x6, 0x20, 0xb4, 0x12, 0x34, 0x56, 0x78, 0x98, 0x76, 0x54, 0x32, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0])
pkt2 = bytearray ([0x45, 0x0, 0x0, 0x1e, 0x16, 0x2e, 0x0, 0x0, 0x40, 0x6, 0xcd, 0x59, 0x66, 0x66, 0x44, 0x44, 0x98, 0x76, 0x54, 0x32, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0])
pkt3 = bytearray ([0x45, 0x0, 0x0, 0x1b, 0x12, 0x67, 0x20, 0xe, 0x20, 0x6, 0x35, 0x58, 0x66, 0x66, 0x44, 0x44, 0x55, 0x44, 0x33, 0x22, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0])
print(basicpacketcheck(pkt1))
# print(basicpacketcheck(pkt2))
# print(basicpacketcheck(pkt3))
print(b'E\x00\x05\xdc^\x88\x00?\x16\x06\x12g\x82\x951M\xc0\xa8\x87A')
print()