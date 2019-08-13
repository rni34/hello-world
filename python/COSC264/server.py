import socket
import sys
import datetime

# AF_INET means IPv4
# Use SOCK_Stream when using TCP
MAGIC_No = 0x497E
IP = '0.0.0.0'
try:
    port_number = sys.argv[1]
except:
    port_number = 1024


def check_header(num):
    if  int.from_bytes(num, 'big') != MAGIC_No:
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
    try:
        connection, address = s.accept()
        print('check accept passed\n')
        print('connection has been established successfully')
        print(datetime.datetime.now())
        print('client IP address {} port number {}\n'.format(address[0], address[1]))
    except:
        print('error while accepting\n')
        raise OSError
    return connection, address

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    check_bind(port_number)
    check_listen(s, 1)
    while True:
        print('ready to work\n')
        connection, addr = check_accept(s)
        file_request = bytearray() + connection.recv(1024)

        header = file_request[:2]
        check_header(header)

        packet_type = file_request[2]
        check_packat_type(packet_type)

        file_name_len = file_request[3:4]


        file_name_bytes = file_request[5:]

        file_name = file_name_bytes.decode('utf-8')
        #recieve the data
        file = open(file_name)
        infile = file.read()

        #gives back the data (it has to be bytes)
        print(infile)
        connection.send(b'Received: ' + infile.encode('utf-8'))
