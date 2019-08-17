import socket
import sys
import datetime
import os
import traceback
import time
# AF_INET means IPv4
# Use SOCK_Stream when using TCP
MAGIC_No = 0x47E
IP = '0.0.0.0'


def check_magic_no(file_request):
    """checks the magic_no_from_client which will be give in byte
    tries to confirm that it is in 0x497E
    raises an error  if its not a 0x497E"""
    try:
        magic_no = file_request[:2].decode()
        magic_no = magic_no.encode('utf-8')
        if int.from_bytes(magic_no, 'big') != 0x497E:
            print('Unacceptable Magic number: Expected 0x497E got {}.'.format(magic_no))
            sys.exit(1)
        print('Magic number accepted.\n')

    except:
        print(traceback.format_exc())


def process_port_number(port):
    """checks the port number, returns true if port is in range 1024 to 64000  """
    try:
        port = int(port)
        if port not in range(1024, 64001):
            print('Unacceptable port number: Must be in range between 1024 to 64000.\n')
            sys.exit(1)
        else:
            print('Port number accepted.\n')
            return port

    except:
        print(traceback.format_exc())
        sys.exit(1)


def attempts_bind(s, port_number):
    """tries to bind the socket from client.py, raises an error if something goes wrong while that"""
    try:
        s.bind((IP, port_number))
        print("The server has been created.\n")

    except:
        print(traceback.format_exc())
        sys.exit(1)


def check_packet_type(header):
    try:
        packet_type = header[2]
        if packet_type != 1:
            print('Unacceptable Packet type: Got type {}, expected type 1.\n'.format(packet_type))
            raise OSError
        print('Packet type accepted.\n')
    except:
        print('Packet type error: Got type {} expected type int.\n'.format(type(packet_type)))
        print('Unable to convert into bytes.')
        print(traceback.format_exc())
        sys.exit(1)


def attempts_listen(s, num):
    try:
        s.listen(num)
        print('Listening...\n')
    except():
        print('Error trying to listen.\n')
        sys.exit(1)


def attempts_accept(s):
    """
    takes a parameter s which stands for a socket and tries to accept the connection from the client.py
    time out is set to 1 second so if it takes more than 1 second to process then it will raise an error
    :param s:
    :return connection, address:
    """
    try:
        connection, address = s.accept()

        print('Connection established successfully')
        print(datetime.datetime.now())
        print('Client IP address {} port number {}\n'.format(socket.getaddrinfo(address[0], address[1])[0][4][0], socket.getaddrinfo(address[0], address[1])[0][4][1]))
    except Exception as e:
        print('Error while accepting {}\n'.format(str(e)))
        sys.exit(1)
    return connection, address


def try_get_file_contents(file_name):
    try:
        file = open(file_name, 'rb')
        infile = file.read()
        file.close()
        print('File read successfully.')
        return infile, 1

    except:
        print(traceback.format_exc())


def try_create_socket():
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    except Exception as e:
        print(e)
        sys.exit(1)
    return s


def try_recv(connection, buffer):
    try:
        return connection.recv(buffer)

    except Exception as e:
        print(traceback.format_exc())
        print('Error while tyring to receive file request {}'.foarmat(e))
        sys.exit(1)


def process_file_name_len(header):
    try:
        return header[3] + header[4]

    except:
        print(traceback.format_exc())
        sys.exit(1)


def check_file_exists(file_name):
    try:
        return os.path.exists(file_name)

    except:
        print(traceback.format_exc())
        sys.exit(1)


def try_send(s, packet):
    try:
        s.sendall(packet)
    except:
        print('Problem occurred while sending')
        print(traceback.format_exc())


def main():

    if len(sys.argv) != 2:
        if len(sys.argv) < 2:
            print('Expected 2 arguments, got only {}'.format(len(sys.argv)))

        else:
            print('Expected 2 arguments, got {}'.format(len(sys.argv)))
        sys.exit(1)

    port_number = process_port_number(sys.argv[1])
    server_socket = try_create_socket()

    with server_socket:
        attempts_bind(server_socket, port_number)
        attempts_listen(server_socket, 1)
        while True:
            print('Awaiting the clients to connect...\n')

            try:
                connection, address = attempts_accept(server_socket)
                connection.settimeout(1)

                file_request_header = try_recv(connection, 5)

                check_magic_no(file_request_header)

                check_packet_type(file_request_header)

                file_name_len = process_file_name_len(file_request_header)

                file_name = try_recv(connection, file_name_len)

                file_exists = check_file_exists(file_name)

                if file_exists:
                    infile, status_code = try_get_file_contents(file_name)

                else:
                    print(file_name, ' does not exist ')
                    infile = b''
                    status_code = 0

                print("{} bytes has been transferred.".format(len(infile)))

                file_response = bytearray() + 0x497E.to_bytes(2, 'big') + 0x02.to_bytes(1, 'big') + status_code.to_bytes(1, 'big') + len(infile).to_bytes(4, 'big') + infile

                try_send(connection, file_response)

                connection.close()

            except socket.timeout:
                print('connection timed out\n')

            except:
                print(traceback.format_exc())
            connection.close()

        server_socket.close()


if __name__ == "__main__":
    main()

# except:
#     print("socket has failed working")
#     status_code = 0x00
#     file_response = bytearray([0x49, 0x7E, 0x02, 0x01])
#     s.close()
#     #connection.send(file_response)
#