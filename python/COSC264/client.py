import socket
import sys



def check_port(port_number):
    """checks the port number, returns true if port is in range 1024 to 64000  """
    if port_number not in range(1024, 64001):
        print('port number not in between 1024 and 64000\n')
        sys.exit(1)
    print('port number check passed\n')



def is_file_valid(file_name):
    '''returns file or raise error'''
    try:
        open(file_name)
    except:
        print('file does not exist\n')
        raise OSError
        sys.exit(1)
    print('file check passed\n')


def create_ip_address(ip_address, port_number):
    try:
        socketfd = socket.getaddrinfo(ip_address, port_number)

    except:
        print('error creating an ip address\n')
        raise OSError
    print('IP address and port number are valid, socket has been created\n')
    return socketfd


try:
    IP = sys.argv[1]
    port_number = sys.argv[2]
    file_name = sys.argv[3]
except:
    IP = '0.0.0.0'
    file_name = 'test.txt'
    port_number = 1024


create_ip_address(IP, port_number)

check_port(port_number)

is_file_valid(file_name)


file_len_bytes = len(file_name).to_bytes(2, 'big')

file_request = bytearray([0x49, 0x7E, 0x01]) + file_len_bytes + file_name.encode('utf-8')

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    # サーバを指定
    s.connect((IP, port_number))
    # サーバにメッセージを送る
    s.sendall(file_request)
    # ネットワークのバッファサイズは1024。サーバからの文字列を取得する
    data = s.recv(1024)
    #
    print(repr(data))