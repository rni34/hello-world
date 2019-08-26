def p_persistent_csma_collision_probability(p):
    return p / (2-p)

print("{:.3f}".format(p_persistent_csma_collision_probability(0.2)))


def p_persistent_csma_access_delay (p):
    return (1 - p) / p

print ("{:.3f}".format(p_persistent_csma_access_delay(0.1)))