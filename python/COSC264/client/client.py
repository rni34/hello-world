import socket
import sys
from threading import Timer


def check_magic_no(magic_no):
    """checks the header which will be give in byte
    tries to confirm that it is in 0x497E
    raises an error  if its not a 0x497E"""
    if magic_no != 0x497E:
        print('Magic No is wrong\n expected 0x497E\n got {}'.format(hex(magic_no)))
        sys.exit(1)
    print('Magic number check passed\n')


def check_packat_type(packet_type):
    try:
        if packet_type != 2:
            print('check packet type error got type {}\nexpected type 1\n'.format(packet_type))
            sys.exit(1)
        print('packet check passed\n')
    except:
        print('check packet type error got type {}\nexpected type int\n'.format(type(packet_type)))
        print('error converting to bytes, its type might not be a byte')
        sys.exit(1)


def check_port(port_number):
    """checks the port number, returns true if port is in range 1024 to 64000  """
    if port_number not in range(1024, 64001):
        print('port number not in between 1024 and 64000\n')
        sys.exit(1)
    print('port number check passed\n')


def check_file_name(file_name):
    try:
        file = open(file_name)
        file.close()
        print('the file you are tyring to download ready exist\n')
        sys.exit(1)
    except:
        print('file has recogised as unknow file, passed check filename  passed \n')



def create_ip_address(ip_address, port_number):
    """ tries to get the addres info of the socket and returns the information of the socket
        raises an error if something goes wrong while getting an information from the socket
    """
    try:
        socketfd = socket.getaddrinfo(ip_address, port_number)
        print(socketfd)

    except:
        print('''error looking for an hostname does not exist or an IP address
given in dotted-decimal notation is not well-formed\n''')
        sys.exit(1)
    print('IP address and port number are valid, socket has been created\n')
    return socketfd


def check_status_code(status_code):
    try:
        if status_code == 1:
            print("status code is 1, keep processing\n")
        elif status_code == 0:
            print('status code is 0, stops processing\n')
        else:
            print('error while processing status code\n')
            raise OSError

        return status_code
    except:
        print('0 or 1 expected from status code but did not get those\n')
        raise OSError


def check_file_length(length_file1, length_file2):
    '''
    param length_file1 which is the size of the file given from the file_response len_file from the server
    param lentgth file2 which is the size of the file that client has got from file_response file_Data
    checks the length of the file that you get from the server with what client has processed from the data.
    if the size are equal then we are good
    if nein then raises an error then exit.

    :param length_file1:
    :param length_file2:
    :return:
    '''

    try:
        if  length_file1 == length_file2:
            print('file length has been checked and passed\n')
        else:
            raise OSError
    except:
        print('length of file name did not match\n')
        print('expected {} but got {}'.format(length_file1, length_file2 ))
        sys.exit(1)


def try_process_header(header):
    """
    takes the parameter s which is a client socket and receives packet and the processes the packet
    :param s:
    :return:
    """
    try:
        check_magic_no((header[0] << 8) + header[1])
        check_packat_type(header[2])
        status_code = check_status_code(header[3])
        print('header has been processed successfully\n')
        return status_code

    except:
        print('Error while trying to process the header\n')
        sys.exit(1)

# def check_file

def try_connect(s, IP, port_number):
    try:
        s.connect(IP, port_number)
    except:
        print("error while trying to connect")
        sys.exit(1)

try:
    IP = sys.argv[1]
    port_number = sys.argv[2]
    file_name = sys.argv[3]

except:
    IP = '132.181.13.184'
    file_name = 'gerno2.txt'
    port_number = 1026


create_ip_address(IP, port_number)

check_port(port_number)

check_file_name(file_name)

file_len_bytes = len(file_name).to_bytes(2, 'big')

file_request = bytearray() + 0x497E.to_bytes(2, 'big') + 0x01.to_bytes(1,'big') + file_len_bytes + file_name.encode('utf-8')

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    # サーバを指定
    try_connect(s, IP, port_number)
    # サーバにメッセージを送る
    s.sendall(file_request)
    # ネットワークのバッファサイズは1024。サーバからの文字列を取得する
    header = s.recv(8)
    status_code = try_process_header(header)
    if status_code == 1:
        file = open(file_name, 'w+')
        infile = s.recv(4096)
        print(infile)
        while infile != b'':
            file.write(infile.decode('utf-8'))
            infile = s.recv(4096)
        file.close()
        data_size_from_server = int.from_bytes(header[4:], 'big')
        file_that_client_made = open(file_name, 'r').read()
        check_file_length(data_size_from_server, len(file_that_client_made))
        file.close()





    # file = s.recv(4)
    #
    #
    # print(data)
    # if len(data) == len(file):
    #     print('yeeeeehaw')
    #     print(data, 'not')
    # else:
    #     print(len(data), len(file))
    # print(repr(data))