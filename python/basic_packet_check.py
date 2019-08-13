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


def basicpacketcheck (pkt):
    first_byte = pkt[0]
    x = 0
    if len(pkt) < 20:
        return 1
    elif (first_byte >> 4) & 0xf != 4:
        return 2

    i = 0
    print(len(pkt))
    while i < len(pkt)-1:
        if i < len(pkt):
            x += (pkt[i] << 8)
            i += 1
            x += pkt[i]
            i += 1

    while x > 0xFFFF:
        X0 = x & 0xFFFF
        X1 = x >> 16
        x = X0 + X1

    if x != 0xFFFF:
        return 3

    length = (pkt[2] << 8) + pkt[3]
    if length != len(pkt):
        return 4

    return True


def destaddress(pkt):
    total_bit = ((pkt[-4] << 24) + (pkt[-3] << 16) + (pkt[-2] << 8) + (pkt[-1]))
    return "({}, '{}.{}.{}.{}')".format(total_bit, pkt[-4], pkt[-3], pkt[-2], pkt[-1])

# print(destaddress(bytearray(b'E\x00\x00\x1e\x04\xd2\x00\x00@\x06\x00\x00\x00\x124V3DUf')))
# # #(860116326, '51.68.85.102')
#
# print(destaddress(bytearray(b'E\x00\x00\x1e\x04\xd2\x00\x00@\x06\x00\x00\x00\x124V\x11"\x88\x99')))
# # (287475865, '17.34.136.153')
#
# print(destaddress(bytearray(b'E\x00\x00\x1e\x04\xd2\x00\x00@\x06\x00\x00\x00\x124V\xa6\xb5\xc4\xb3')))
# # (2796930227, '166.181.196.179')

# print(destaddress(bytearray([0x0c,0x29,0xae ,0x8e, 0x88 ,0x00 ,0x50 ,0x56 ,0xe0 ,0x07 ,0x01 ,0x08 ,0x00, 0x45, 0x00])))
# print(destaddress(bytearray([0x00 ,0x2c ,0x9c ,0x3f ,0x00 ,0x00 ,0x80 ,0x06  ,0x57 ,0x35 ,0x5b ,0xbd ,0x59 ,0x58])))
# print(destaddress(bytearray([0xd1 ,0x99 ,0x00 ,0x50, 0xcc ,0xb9 ,0x02 ,0xbf  ,0x5c ,0xfa ,0x4f ,0xbb ,0xeb ,0x82 ,0x60])))
# print(destaddress(bytearray([0x12, 0xf0 ,0xed ,0xcc ,0x00 ,0x00 ,0x02 ,0x04  ,0x05 ,0xb4])))

print(destaddress(bytearray([0x5b, 0xbd, 0x59, 0x58])))
print(destaddress(bytearray([0xc0, 0xa8, 0xd1, 0x99])))

def payload (pkt):
    hdrlen = pkt[0] & 0xF0 >> 4
    return pkt[hdrlen * 32 // 8:]
# print(payload(bytearray(b'E\x00\x00\x17\x00\x00\x00\x00@\x06i\x8d\x11"3DUfw\x88\x10\x11\x12')))

#pkt1 = bytearray ([0x45, 0x0, 0x0, 0x1e, 0x4, 0xd2, 0x0, 0x0, 0x40, 0x6, 0x20, 0xb4, 0x12, 0x34, 0x56, 0x78, 0x98, 0x76, 0x54, 0x32, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0])
# pkt2 = bytearray ([0x45, 0x0, 0x0, 0x1e, 0x16, 0x2e, 0x0, 0x0, 0x40, 0x6, 0xcd, 0x59, 0x66, 0x66, 0x44, 0x44, 0x98, 0x76, 0x54, 0x32, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0])
# pkt3 = bytearray ([0x45, 0x0, 0x0, 0x1b, 0x12, 0x67, 0x20, 0xe, 0x20, 0x6, 0x35, 0x58, 0x66, 0x66, 0x44, 0x44, 0x55, 0x44, 0x33, 0x22, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0])
#print(basicpacketcheck(pkt1))
# print(basicpacketcheck(pkt2))
# print(basicpacketcheck(pkt3))


def revisedcompose (hdrlen, tosdscp, identification, flags, fragmentoffset, timetolive, protocoltype, sourceaddress, destinationaddress, payload):
    headerchecksum = 0
    version = 4
    totallength = hdrlen * 4 + len(payload)
    if version != 4:
        return 1
    elif hdrlen < 5 or len(bin(hdrlen)) > 6:
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

        res = bytearray()
        first_byte = (version << 4) | hdrlen
        tosdscp = (tosdscp << 2)
        flag_byte = (flags << 13) + (fragmentoffset)
        list1 = [first_byte, tosdscp, totallength, identification, flag_byte, timetolive, protocoltype, headerchecksum, sourceaddress, destinationaddress]
        acc = 0
        while acc < len(list1):
            if acc > 7:
                res += list1[acc].to_bytes(4, 'big')
            elif acc < 2 or acc == 5 or acc == 6:
                res += list1[acc].to_bytes(1, 'big')
            else:
                res += list1[acc].to_bytes(2, 'big')
            acc += 1

    for i in range(hdrlen * 4 - len(res)):
        res.append(0x00)

    X = 0
    for i in range(len(res) // 2):
        X += (res[2 * i] << 8) | res[2 * i + 1]

    while X > 0xFFFF:
        X0 = X & 0xFFFF
        X1 = X >> 16
        X = X0 + X1

    X = X ^ 0xFFFF
    x1 = X >> 8
    x2 = X & 255
    res[10] = x1
    res[11] = x2

    for byte in payload:
        res.append(byte)
    return res
# print(revisedcompose (6, 24, 4711, 0, 22, 64, 0x06, 0x22334455, 0x66778899, bytearray([0x10, 0x11, 0x12, 0x13, 0x14, 0x15])))
# print(revisedcompose (6, 24, 4711, 0, 22, 64, 0x06, 0x22334455, 0x66778899, bytearray([0x10, 0x11, 0x12, 0x13, 0x14, 0x15]))==bytearray(b'F`\x00\x1e\x12g\x00\x16@\x06\x11e"3DUfw\x88\x99\x00\x00\x00\x00\x10\x11\x12\x13\x14\x15'))
# print(bytearray(b'F`\x00\x1e\x12g\x00\x16@\x06\x11e"3DUfw\x88\x99\x00\x00\x00\x00\x10\x11\x12\x13\x14\x15'))
# # # print(int.from_bytes(b'\x1e','big'))



# print(revisedcompose (6, 24, 4711, 0, 22, 64, 0x06, 0x22334455, 0x66778899, bytearray([0x10, 0x11, 0x12, 0x13, 0x14, 0x15]))==bytearray(b'F`\x00\x1e\x12g\x00\x16@\x06\x11e"3DUfw\x88\x99\x00\x00\x00\x00\x10\x11\x12\x13\x14\x15'))
#
# print(revisedcompose(16,0,4000,0,63,22,0x06, 2190815565, 3232270145, bytearray([]))==2)
# print(revisedcompose(4,0,4000,0,63,22,0x06, 2190815565, 3232270145, bytearray([]))==2)
#
# print(revisedcompose(5,64,4000,0,63,22,0x06, 2190815565, 3232270145, bytearray([]))==3)
#
# print(revisedcompose(5,63,0x10000,0,63,22,0x06, 2190815565, 3232270145, bytearray([]))==5)
#
# print(revisedcompose(5,63,4711,8,63,22,0x06, 2190815565, 3232270145, bytearray([]))==6)
# print(revisedcompose(5,63,4711,0,8192,22,0x06, 2190815565, 3232270145, bytearray([]))==7)
# print(revisedcompose(5,63,4711,0,8191,256,0x06, 2190815565, 3232270145, bytearray([]))==8)
# print(revisedcompose(5,63,4711,0,8191,64,256, 2190815565, 3232270145, bytearray([]))==9)
# print(revisedcompose(5,63,4711,0,8191,64,0x06, 4294967296, 3232270145, bytearray([]))==11)
# print(revisedcompose(5,63,4711,0,8191,64,0x06, 2190815565, 4294967296, bytearray([]))==12)
# print(revisedcompose (5, 24, 4711, 0, 22, 64, 0x06, 0x22334455, 0x66778899, bytearray([0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17]))==bytearray(b'E`\x00\x1c\x12g\x00\x16@\x06\x12g"3DUfw\x88\x99\x10\x11\x12\x13\x14\x15\x16\x17'))