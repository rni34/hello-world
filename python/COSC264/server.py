import socket
import sys
import datetime
import time
# AF_INET means IPv4
# Use SOCK_Stream when using TCP
MAGIC_No = 0x497E
IP = '132.181.13.184'
try:
    port_number = sys.argv[1]
except:
    port_number = 1026


def check_header(num):
    """checks the header which will be give in byte
    tries to confirm that it is in 0x497E
    raises an error  if its not a 0x497E"""
    if int.from_bytes(num, 'big') != MAGIC_No:
        print('Magic No is wrong\n expected 0x497E\n got {}'.format(hex(num)))
        raise OSError
    print('Magic number check passed\n')


def port_number_checker(port_number):
    """checks the port number, returns true if port is in range 1024 to 64000  """
    if port_number not in range(1024, 64001):
        print('port number is not in range 1024 to 64000\n')
        raise OSError
    print('port number check passed\n')


def check_bind(port_number):
    '''tries to bind the socket from client.py, raises an error if something goes wrong while that'''
    try:
        s.bind((IP, port_number))
        print("server has been created\n")
    except OSError:
        print("unable to bind the socket, could be IP already existed")
        sys.exit(1)


def check_packat_type(packet_type):
    try:
        if packet_type != 1:
            print('check packet type error got type {}\nexpected type 1\n'.format(packet_type))
            raise OSError
        print('packet check passed\n')
    except:
        print('check packet type error got type {}\nexpected type int\n'.format(type(packet_type)))
        print('error converting to bytes, its type might not be a byte')
        raise OSError


def check_listen(s, num):
    try:
        s.listen(num)
        print('check listen passed\n')
    except:
        print('error trying to listen\n')
        raise OSError


def check_accept(s):
    """
    takes a parameter s which stands for a socket and tries to accept the connection from the client.py
    time out is set to 1 second so if it takes more than 1 second to process then it will raise an error
    :param s:
    :return connection, address:
    """
    try:
        conection, address = s.accept()
        s.settimeout(1)
        print('check accept passed\n')
        print('connection has been established successfully')
        print(datetime.datetime.now())
        print('client IP address {} port number {}\n'.format(address[0], address[1]))
    except:
        print('error while accepting\n')
        raise OSError
    return conection, address


def is_file_valid(file_name):
    try:
        file = open(file_name)
        infile = file.read()
        file.close()
        print('check open passed')
    except:
        print('error while opening a file')
        raise OSError
    return infile



s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
with s:
    check_bind(port_number)
    check_listen(s, 1)
    while True:
        print('ready to accept connection from clients\n')
        connection, addr = check_accept(s)
        with connection:
            file_request = bytearray() + connection.recv(4096)

            header = file_request[:2]
            check_header(header)

            packet_type = file_request[2]
            check_packat_type(packet_type)

            file_name_len = file_request[3] + file_request[4]

            file_name = file_request[5:].decode('utf-8')

            infile = is_file_valid(file_name)
            print(len(infile), len(infile).to_bytes(4, 'big'))
            #gives back the data (it has to be bytes)
            infile_bytes = infile.encode('utf-8')
            print("{} bytes has been transferred".format(len(infile_bytes)))
            status_code = 0x01
            file_response = bytearray() + 0x497E.to_bytes(2, 'big') + 0x02.to_bytes(1,'big') + 0x01.to_bytes(1,'big') + len(infile_bytes).to_bytes(4, 'big') + infile_bytes
            print(file_response)
            connection.send(file_response)
            s.settimeout(None)

# except:
#     print("socket has failed working")
#     status_code = 0x00
#     file_response = bytearray([0x49, 0x7E, 0x02, 0x01])
#     s.close()
#     #connection.send(file_response)
#
