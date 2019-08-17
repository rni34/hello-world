import socket
import sys
import os
import traceback

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


def process_port_number(port):
    """
    checks the port number, returns true if port is in range 1024 to 64000
    if not the print the error and exit

    :param port:
    :return:
    """
    try:
        port = int(port)
        if port not in range(1024, 64001):
            print('Unacceptable port number: Must be in range between 1024 to 64000.\n')
            sys.exit(1)
        else:
            print('Port number is valid. Your port number is {}\n'.format(port))
            return port

    except:
        print(traceback.format_exc())
        sys.exit(1)


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


def try_connect(s, socket_fd):
    try:
        s.connect(socket_fd)

    except:
        print("Error while trying to connect:")
        print(traceback.format_exc())
        s.close()
        sys.exit(1)


def try_recv(s, buffer):
    try:
        return s.recv(buffer)

    except:
        print("Error while trying to receive")
        print(traceback.format_exc())
        s.close()
        sys.exit(1)


def try_get_addrinfo(ip_address, port_number):
    """
    Tries to get a ip address and port number
    if it fails then prints the error and exit
    I go through this process because ip_address could be a name of the host instead of the
    actual dotted decimal notation

    :param ip_address:
    :param port_number:
    :return ip_address, port_number:
    """
    try:
        return socket.getaddrinfo(ip_address, port_number)[0][4]

    except:
        print(traceback.format_exc())
        sys.exit(1)


def try_send(s, packet):
    """
    tries to send the packet if this process works then return True
    if not then print the error then exit
    :param s:
    :param packet
    """
    try:
        s.sendall(packet)

    except:
        print('Problem occurred while sending')
        print(traceback.format_exc())
        sys.exit(1)


def check_arguments():
    """
    checks the arguments that I was given
    it should be just server.py and port_number but you never know!

    checks the number of arguments if not 4 then print the error and exit
    :return nothing :
    """
    try:
        if len(sys.argv) != 4:
            if len(sys.argv) < 4:
                print('Expected 4 arguments, got only {}'.format(len(sys.argv)))

            else:
                print('Expected 4 arguments, got {}'.format(len(sys.argv)))
            sys.exit(1)
    except:
        print(traceback.format_exc())
        sys.exit(1)


def main():
    try:
        check_arguments()

        ip_address = sys.argv[1]

        port_number = process_port_number(sys.argv[2])

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

        try_connect(s, socket_fd)

        try_send(s, file_request)

        header = try_recv(s, 8)

        check_magic_no(header)

        check_packet_type(header)

        status_code = check_status_code(header)

        try:
            if status_code == 1:
                new_file = open(file_name, 'wb+')
                while True:
                    infile = s.recv(4096)
                    print(infile)
                    if len(infile) <= 4096:
                        new_file.write(infile)
                        break
                    new_file.write(infile)

            print('File has been successfully downloaded!\n')
            new_file.close()


        except Exception as e:
            print('Problem occurred while processing the file {}'.format(e))

            data_size_from_server = int.from_bytes(header[4:], 'big')

            file_that_client_made = open(file_name, 'rb').read()

            check_file_length(data_size_from_server, len(file_that_client_made))

            new_file.close()

            s.close()

            sys.exit(1)

        s.close()


if __name__ == "__main__":
    main()