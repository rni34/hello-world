import socket
import sys
import datetime
MAGIC_NO = 0x497E #18814

def file_request_check(file_request):
    """Takes the bytearray of the file request and checks if it's valid,
       if valid, returns the requested filename"""
    filename_len = (file_request[3] << 8) + file_request[4]
    if (file_request[0] << 8) + file_request[1] != MAGIC_NO:
        print('ERROR: Invalid Magic Number in FileRequest')
        return False
    if file_request[2] != 1:
        print('ERROR: Invalid Type in FileRequest')
        return False
    if filename_len < 1 or filename_len > 1024:
        print('ERROR: Filename Length is Invalid')
        return False
    else:
        return True

def file_response_const(filename):
    """Contructs the file response"""
    try:
        f = open(filename, 'rb')
    except FileNotFoundError:
        status = 0
    else:
        file_data = f.read()
        data_length = len(file_data).to_bytes(4, 'big')
        status = 1

    magic_no = MAGIC_NO.to_bytes(2, 'big')
    type = 0x02.to_bytes(1, 'big')
    status_code = status.to_bytes(1, 'big')

    if status == 1:
        return magic_no + type + status_code + data_length + file_data
    else:
        return magic_no + type + status_code #File not found

def main():
    IP = '0.0.0.0'
    port_no = int(sys.argv[1])

    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind((IP, port_no))
    s.listen()

    while True:
        clientsocket, address = s.accept()
        with clientsocket:
            clientsocket.settimeout(1)
            curr_time = datetime.datetime.now()
            print("Connection from {} at {} has been established".format(address, curr_time))

            file_request = clientsocket.recv(5)
            if file_request_check(file_request):
                filename_len = (file_request[3] << 8) + file_request[4]

                clientsocket.settimeout(None) #Transmitting file, don't timout while sending

                filename = clientsocket.recv(filename_len)
                response_file = file_response_const(filename.decode('utf-8'))
                clientsocket.send(response_file)

            clientsocket.close()

main()
