import socket
import sys
import os
from threading import Timer

IP = sys.argv[1]
PORT_NUMBER = int(sys.argv[2])
file_name = sys.argv[3]


# try:
#     IP = sys.argv[1]
#     PORT_NUMBER = int(sys.argv[2])
#     file_name = sys.argv[3]
#
# except:
#     #IP = '132.181.13.52' #jack
#     IP = '0.0.0.0'
#     #IP = '132.181.13.40'
#     file_name = 'bean.png'
#     PORT_NUMBER = 1024


def check_magic_no(magic_no):
    """checks the magic_no_from_client which will be give in byte
    tries to confirm that it is in 0x497E
    raises an error  if its not a 0x497E"""
    if int.from_bytes(magic_no, 'big') != 0x497E:
        print('Unacceptable Magic number: Expected 0x497E got {}'.format(hex(magic_no)))
        sys.exit(1)
    print('Magic number acceptable.\n')


def check_packet_type(packet_type):
    try:
        if packet_type != 2:
            print('Unacceptable Packet type: Got type {}, expected type 1.\n'.format(packet_type))
            sys.exit(1)
        print('Packet type accepted.\n')
    except():
        print('Packet type error: Got type {} expected type int.\n'.format(type(packet_type)))
        print('Unable to convert into bytes.')
        sys.exit(1)


def check_port(port_number):
    """checks the port number, returns true if port is in range 1024 to 64000  """
    if port_number not in range(1024, 64001):
        print('port number not in between 1024 and 64000\n')
        sys.exit(1)
    print('Check Port Number: Passed.\n')


def check_file_exists(file_name):
    try:
        if os.path.exists(file_name) is True:
            print('File already exists.\n')
            sys.exit(1)
        else:
            print('Check file: Passed.\n')
    except Exception as e:
        print('{}'.format(str(e)))
    # except Exception as e:
    #     if e == FileNotFoundError:
    #         print('new_file has recognised as unknown new_file, passed check filename  passed \n')
    #     else:
    #         print('error while tyring to open the file {}'.format(str(e)))


# def create_ip_address(ip_address, PORT_NUMBER):
#     """ tries to get the addres info of the socket and returns the information of the socket
#         raises an error if something goes wrong while getting an information from the socket
#     """
#     try:
#         socketfd = socket.getaddrinfo(ip_address, PORT_NUMBER)
#         print(socketfd)
#
#     except:
#         print('error looking for an hostname does not exist or an IP address given in dotted-decimal notation is not well-formed\n')
#         sys.exit(1)
#     print('IP address and port number are valid, socket has been created\n')
#     return socketfd


def check_status_code(status_code):
    try:
        if status_code == 1:
            print("Status code: 1 (Processing)\n")

        elif status_code == 0:
            print('Status code: 0 (Stopped process)\n')
            sys.exit(1)

        else:
            print('Error while processing: File does not exists. {}\n'.format(status_code))
            sys.exit(1)
        return status_code

    except Exception as e:
        print(e)
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
        sys.exit(1)


def try_process_header(header):
    """
    takes the parameter s which is a client socket and receives packet and the processes the packet
    :param s:
    :return:
    """
    try:
        check_magic_no(((header[0] << 8) + header[1]).to_bytes(2, 'big'))
        check_packet_type(header[2])
        status_code = check_status_code(header[3])
        print('Header has successfully processed.\n')
        return status_code

    except Exception as e:
        print('Error while trying to process the header: {}\n'.format(str(e)))
        sys.exit(1)


def try_connect(s, IP, port_number):
    try:
        s.connect((IP, port_number))

    except Exception as e:
        print("Error while trying to connect: " + str(e))
        sys.exit(1)


def main():
    # create_ip_address(IP, PORT_NUMBER)

    check_port(PORT_NUMBER)

    check_file_exists(file_name)

    file_len_bytes = len(file_name).to_bytes(2, 'big')

    file_request = bytearray() + 0x497E.to_bytes(2, 'big') + 0x01.to_bytes(1, 'big') + file_len_bytes + file_name.encode('utf-8')

    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        # サーバを指定
        try_connect(s, IP, PORT_NUMBER)
        # サーバにメッセージを送る
        s.sendall(file_request)
        # ネットワークのバッファサイズは1024。サーバからの文字列を取得する
        header = s.recv(8)
        status_code = try_process_header(header)
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
            new_file.close()

        except Exception as e:
            print('Problem occurred while processing the file {}'.format(e))



            data_size_from_server = int.from_bytes(header[4:], 'big')

            file_that_client_made = open(file_name, 'rb').read()

            check_file_length(data_size_from_server, len(file_that_client_made))

            new_file.close()


if __name__ == "__main__":
    main()