def revisedcompose(hdrlen, tosdscp, identification, flags, fragmentoffset, timetolive, protocoltype, sourceaddress,
                   destinationaddress, payload):
    """hdrlen is in 32bits
    len(byte_stream) gives 4 * this
    we need 16 bit numbers from 8 bit"""
    byte_stream = bytearray()
    headerchecksum = 0
    version = 4
    totallength = hdrlen * 4 + len(payload)
    if version != 4:
        return 1
    if hdrlen.bit_length() > 4 or hdrlen < 5:
        return 2
    if tosdscp.bit_length() > 6 or tosdscp < 0:
        return 3
    if totallength.bit_length() > 16 or totallength < 0:
        return 4
    if identification.bit_length() > 16 or identification < 0:
        return 5
    if flags.bit_length() > 3 or flags < 0:
        return 6
    if fragmentoffset.bit_length() > 13 or fragmentoffset < 0:
        return 7
    if timetolive.bit_length() > 8 or timetolive < 0:
        return 8
    if protocoltype.bit_length() > 8 or protocoltype < 0:
        return 9
    if headerchecksum.bit_length() > 16 or headerchecksum < 0:
        return 10
    if sourceaddress.bit_length() > 32 or sourceaddress < 0:
        return 11
    if destinationaddress.bit_length() > 32 or destinationaddress < 0:
        return 12

    a = (version << 4) | hdrlen
    byte_stream.append(a)
    # ----------------------------------------------
    b = (tosdscp << 2)
    byte_stream.append(b)
    # -------------------------------------------
    c = (totallength >> 8)
    byte_stream.append(c)
    d = (totallength & 255)
    byte_stream.append(d)
    # -----------------------------------------------
    e = (identification >> 8)
    byte_stream.append(e)
    f = (identification & 255)
    byte_stream.append(f)
    # ----------------------------------------
    g = (flags << 5) | (fragmentoffset >> 8)
    byte_stream.append(g)
    x = (fragmentoffset & 255)
    byte_stream.append(x)
    # ----------------------------------------
    byte_stream.append(timetolive)
    byte_stream.append(protocoltype)
    # ---------------------------------------
    h = (headerchecksum >> 8)
    byte_stream.append(h)
    i = (headerchecksum & 255)
    byte_stream.append(i)
    # ------------------------------------------
    j = (sourceaddress >> 24)
    byte_stream.append(j)
    k = (sourceaddress >> 16) & 255
    byte_stream.append(k)
    l = (sourceaddress >> 8) & 255
    byte_stream.append(l)
    m = sourceaddress & 255
    byte_stream.append(m)
    # ---------------------------------------
    n = (destinationaddress >> 24)
    byte_stream.append(n)
    o = (destinationaddress >> 16) & 255
    byte_stream.append(o)
    p = (destinationaddress >> 8) & 255
    byte_stream.append(p)
    q = destinationaddress & 255
    byte_stream.append(q)
    for i, element in enumerate(byte_stream):
        if element <= 0xFF:
            if element != totallength:
                x = element.to_bytes(1, byteorder='big')

            else:
                x = element.to_bytes(2, byteorder='big')

        elif element <= 0xFFF:
            x = element.to_bytes(2, byteorder='big')

        else:
            x = element.to_bytes(4, byteorder='big')
        print(i, x, element)

    for i in range(hdrlen * 4 - len(byte_stream)):
        byte_stream.append(0x00)

    X = 0
    for i in range(len(byte_stream) // 2):
        X += (byte_stream[2 * i] << 8) | byte_stream[2 * i + 1]

    while X > 0xFFFF:
        X0 = X & 0xFFFF
        X1 = X >> 16
        X = X0 + X1

    X = X ^ 0xFFFF
    x1 = X >> 8
    x2 = X & 255
    byte_stream[10] = x1
    byte_stream[11] = x2

    for byte in payload:
        byte_stream.append(byte)


    return byte_stream

print(revisedcompose (6, 24, 4711, 0, 22, 64, 0x06, 0x22334455, 0x66778899, bytearray([0x10, 0x11, 0x12, 0x13, 0x14, 0x15])))
