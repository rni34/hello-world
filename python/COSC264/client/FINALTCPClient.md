import socket
import sys
MAGIC_NO = 0x497E #18814

def file_request_constr(filename):
    """Takes filename to request, and constructs a proper file request record to
       send to the server
    """
    magic_no = 0x497E.to_bytes(2, 'big')
    type = 0x01.to_bytes(1, 'big')
    filename_len = len(filename).to_bytes(2, 'big')

    return magic_no + type + filename_len + filename.encode('utf-8')

def file_response_check(s, file_response):
    """Takes the bytearray of the file response and checks if it's valid,
       if valid, returns the requested filename"""
    if (file_response[0] << 8) + file_response[1] != MAGIC_NO:
        print('ERROR: Invalid Magic Number in FileReponse')
        s.close()
        sys.exit(1)
    if file_response[2] != 2:
        print('ERROR: Invalid Type in FileResponse')
        s.close()
        sys.exit(1)
    if file_response[3] == 0:
        print('ERROR: File could not be found')
        s.close()
        sys.exit(1)
    if file_response[3] != 1:
        print('ERROR: Invalid Status Code')
        s.close()
        sys.exit(1)
    else:
        return 0

def check_data_length(file_response, num_bytes):
    """Checks the length of the written file is equal to the length of the file
       given in the fileresponse header
    """
    header_data_length = int.from_bytes(file_response[4:8], 'big')
    if header_data_length != num_bytes:
        print('ERROR File Size different than what was expected')

def check_port_number(port_number):
    """Checks the port number is between 1024 and 64000 (including)"""
    if port_number < 1024 or port_number > 64000:
        print('ERROR: Invalid Port Number')
        sys.exit(1)

def check_filename(filename):
    """Checks if the file already exists on this machine"""
    try:
        f = open(filename)
        f.close()
    except IOError:
        return True
    else:
        print('ERROR: File already exists')
        sys.exit(1)

def main():
    address = sys.argv[1]
    port_no = int(sys.argv[2])
    check_port_number(port_no)
    socket_fd = socket.getaddrinfo(address, port_no)[0][4]

    filename = str(sys.argv[3])
    check_filename(filename)

    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect(socket_fd)
    s.settimeout(1)

    file_request = file_request_constr(filename)
    s.send(file_request)

    file_response_header = s.recv(8) #Recieve the header of fileresponse
    file_response_check(s, file_response_header)

    file = open(filename, 'wb+')
    num_bytes = 0
    while True:
        s.settimeout(1)
        requested_file = s.recv(4096) #Recieve file response data
        if len(requested_file) <= 0:
            file.close()
            break
        num_bytes += len(requested_file)
        file.write(requested_file)
    check_data_length(file_response_header, num_bytes)
    print("Retrieved {} from {}. {} bytes were received".format(filename, address, num_bytes))
    s.close()

main()
