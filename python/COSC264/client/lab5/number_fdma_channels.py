import math


def number_fdma_channels (b_hz, g_hz, u_hz):
    return math.ceil((b_hz - u_hz) / (g_hz + u_hz))


print(number_fdma_channels(1000000, 1000, 30000))


def number_tdma_users (s_s, g_s, u_s):
    return math.floor((s_s) / (g_s + u_s))


print(number_tdma_users(0.1, 0.001, 0.005))
