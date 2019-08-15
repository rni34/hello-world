import socket
import sys
import datetime
import os
import time
# AF_INET means IPv4
# Use SOCK_Stream when using TCP
MAGIC_No = 0x47E
IP = '0.0.0.0'
PORT_NUMBER = int(sys.argv[1])


def check_magic_no(magic_no):
    """checks the magic_no_from_client which will be give in byte
    tries to confirm that it is in 0x497E
    raises an error  if its not a 0x497E"""
    magic_no = magic_no.encode('utf-8')
    if int.from_bytes(magic_no, 'big') != 0x497E:
        print('Unacceptable Magic number: Expected 0x497E got {}.'.format(hex(magic_no)))
        sys.exit(1)
    print('Magic number accepted.\n')


def check_port_number(port):
    """checks the port number, returns true if port is in range 1024 to 64000  """
    try:
        if port not in range(1024, 64001):
            print('Unacceptable port number: Must be in range between 1024 to 64000.\n')
            sys.exit(1)
        else:
            print('Port number accepted.\n')

    except Exception as e:
        print('Error while checking the port number {}.'.format(e))
        sys.exit(1)


def check_bind(s):
    """tries to bind the socket from client.py, raises an error if something goes wrong while that"""
    try:
        s.bind((IP, PORT_NUMBER))
        print("The server has been created.\n")
    except OSError:
        print("Unable to bind the socket: IP already in used.")
        sys.exit(1)


def check_packet_type(packet_type):
    try:
        if packet_type != 1:
            print('Unacceptable Packet type: Got type {}, expected type 1.\n'.format(packet_type))
            raise OSError
        print('Packet type accepted.\n')
    except():
        print('Packet type error: Got type {} expected type int.\n'.format(type(packet_type)))
        print('Unable to convert into bytes.')
        sys.exit(1)


def check_listen(s, num):
    try:
        s.listen(num)
        print('Listening...\n')
    except():
        print('Error trying to listen.\n')
        sys.exit(1)


def check_accept(s):
    """
    takes a parameter s which stands for a socket and tries to accept the connection from the client.py
    time out is set to 1 second so if it takes more than 1 second to process then it will raise an error
    :param s:
    :return connection, address:
    """
    try:
        connection, address = s.accept()
        s.settimeout(1)
        print('Connection established successfully')
        print(datetime.datetime.now())
        print('Client IP address {} port number {}\n'.format(address[0], address[1]))
    except Exception as e:
        print('Error while accepting {}\n'.format(str(e)))

        sys.exit(1)
    return connection, address


def is_file_valid(file_name):
    try:
        file = open(file_name, 'rb')
        infile = file.read()
        file.close()
        print('File read successfully.')
    except Exception as e:
        print('Error while opening a file', str(e), '\n')
        sys.exit(1)

    return infile


def main():
    status_code = 1
    try:
        check_port_number(PORT_NUMBER)

    except Exception as e:
        print('Error while trying to check the port number {}\n'.format(e))
        sys.exit(1)

    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    with s:
        check_bind(s)
        check_listen(s, 1)
        while True:
            print('Awaiting the clients to connect...\n')
            connection, address = check_accept(s)
            with connection:
                file_request = bytearray() + connection.recv(4096)

                magic_no_from_client = file_request[:2].decode()

                check_magic_no(magic_no_from_client)

                check_packet_type(file_request[2])

                # file_name_len = file_request[3] + file_request[4]

                file_name = file_request[5:].decode('utf-8')

                if os.path.exists(file_name):
                    infile = is_file_valid(file_name)
                else:
                    infile = b''
                    status_code = 0

                print("{} bytes has been transferred.".format(len(infile)))

                file_response = bytearray() + 0x497E.to_bytes(2, 'big') + 0x02.to_bytes(1, 'big') + \
                    status_code.to_bytes(1, 'big') + len(infile).to_bytes(4, 'big') + infile

                connection.send(file_response)

                s.settimeout(None)


if __name__ == "__main__":
    main()

# except:
#     print("socket has failed working")
#     status_code = 0x00
#     file_response = bytearray([0x49, 0x7E, 0x02, 0x01])
#     s.close()
#     #connection.send(file_response)
#