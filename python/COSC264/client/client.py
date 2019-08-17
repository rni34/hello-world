import socket
import sys
import os
import traceback
from threading import Timer


# try:
#     IP = sys.argv[1]
#     port_number = int(sys.argv[2])
#     file_name = sys.argv[3]
#
# except:
#     #IP = '132.181.13.52' #jack
#     IP = '0.0.0.0'
#     #IP = '132.181.13.40'
#     file_name = 'bean.png'
#     port_number = 1024


def check_magic_no(header):
    """checks the magic_no_from_client which will be give in byte
    tries to confirm that it is in 0x497E
    raises an error  if its not a 0x497E"""
    try:
        magic_no = ((header[0] << 8) + header[1]).to_bytes(2, 'big')
        if int.from_bytes(magic_no, 'big') != 0x497E:
            print('Unacceptable Magic number: Expected 0x497E got {}'.format(magic_no))
            sys.exit(1)
        print('Magic number acceptable.\n')

    except:
        print(traceback.format_exc())


def check_packet_type(header):
    try:
        packet_type = header[2]
        if packet_type != 2:
            print('Unacceptable Packet type: Got type {}, expected type 1.\n'.format(packet_type))
            sys.exit(1)
        print('Packet type accepted.\n')

    except():
        print('Packet type error: Got type {} expected type int.\n'.format(type(packet_type)))
        print('Unable to convert into bytes.')
        print(traceback.format_exc())
        sys.exit(1)


def check_port(port_number):
    port_number = int(port_number)
    """checks the port number, returns true if port is in range 1024 to 64000  """
    try:
        if port_number not in range(1024, 64001):
            print('port number not in between 1024 and 64000\n')
            sys.exit(1)
        print('Check Port Number: Passed.\n')
        return port_number

    except:
        print(traceback.format_exc())


def check_file_exists(file_name):
    try:
        if os.path.exists(file_name) is True:
            print('File already exists.\n')
            sys.exit(1)
        else:
            print('Check file: Passed.\n')

    except:
        print(traceback.format_exc())
        sys.exit(1)
    # except Exception as e:
    #     if e == FileNotFoundError:
    #         print('new_file has recognised as unknown new_file, passed check filename  passed \n')
    #     else:
    #         print('error while tyring to open the file {}'.format(str(e)))


# def create_ip_address(ip_address, port_number):
#     """ tries to get the addres info of the socket and returns the information of the socket
#         raises an error if something goes wrong while getting an information from the socket
#     """
#     try:
#         socketfd = socket.getaddrinfo(ip_address, port_number)
#         print(socketfd)
#
#     except:
#         print('error looking for an hostname does not exist or an IP address given in dotted-decimal notation is not well-formed\n')
#         sys.exit(1)
#     print('IP address and port number are valid, socket has been created\n')
#     return socketfd


def check_status_code(header):
    status_code = header[3]
    try:
        if status_code == 1:
            print("Status code: 1 (Processing)\n")

        elif status_code == 0:
            print('Status code: 0 (Stopped process)\n')
            print('Server does not have the file you requested\n')
            sys.exit(1)
        else:
            print('Error while checking status code: File does not exists. {}\n'.format(status_code))
            sys.exit(1)

        return status_code

    except:
        print(traceback.format_exc())
        sys.exit(1)


def check_file_length(length_file1, length_file2):
    '''
    param length_file1 which is the size of the new_file given from the file_response len_file from the server
    param lentgth file2 which is the size of the new_file that client has got from file_response file_Data
    checks the length of the new_file that you get from the server with what client has processed from the data.
    if the size are equal then we are good
    if nein then raises an error then exit.

    :param length_file1:
    :param length_file2:
    :return:
    '''

    try:
        if length_file1 == length_file2:
            print('The file has successfully downloaded.\n')
        else:
            raise OSError
    except:
        print('length of new_file name did not match\n')
        print('Expected {} but, got {}.'.format(length_file1, length_file2 ))
        print(traceback.format_exc())
        sys.exit(1)


def try_connect(s, ip_address, port_number):
    try:
        s.connect((ip_address, port_number))

    except:
        print("Error while trying to connect:")
        print(traceback.format_exc())
        sys.exit(1)


def try_recv(s, buffer):
    try:
        return s.recv(buffer)

    except:
        print("Error while trying to receive")
        print(traceback.format_exc())
        sys.exit(1)


def try_get_addrinfo(ip_address, port_number):
    try:
        return socket.getaddrinfo(ip_address, port_number)[0][4]

    except:
        print(traceback.format_exc())
        sys.exit(1)


def try_send(s, file_request):
    try:
        s.sendall(file_request)
    except:
        print('Problem occurred while sending')
        print(traceback.format_exc())


def main():
    # create_ip_address(ip_address, port_number)
    try:
        ip_address = sys.argv[1]

        port_number = int(sys.argv[2])

        port_number = check_port(port_number)

        socket_fd = try_get_addrinfo(ip_address, port_number)

        print('client log', socket_fd)

        file_name = sys.argv[3]


    except:
        print(traceback.format_exc())
        sys.exit(1)

    check_file_exists(file_name)

    file_len_bytes = len(file_name).to_bytes(2, 'big')

    file_request = bytearray() + 0x497E.to_bytes(2, 'big') + 0x01.to_bytes(1, 'big') + file_len_bytes + file_name.encode('utf-8')

    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:

        try_connect(s, ip_address, port_number)

        s.sendall(file_request)

        header = try_recv(s, 8)

        check_magic_no(header)

        check_packet_type(header)

        status_code = check_status_code(header)

        try:
            if status_code == 1:
                new_file = open(file_name, 'wb+')
                done = False
                while not done:
                    infile = s.recv(4096)
                    if len(infile) <= 0:
                        done = True
                    else:
                        new_file.write(infile)
            print('File has been successfully downloaded!\n')
            new_file.close()

        except Exception as e:
            print('Problem occurred while processing the file {}'.format(e))

            data_size_from_server = int.from_bytes(header[4:], 'big')

            file_that_client_made = open(file_name, 'rb').read()

            check_file_length(data_size_from_server, len(file_that_client_made))

            new_file.close()


if __name__ == "__main__":
    main()