def generate_symbol_table(assembly_code):
    symbol_table = {}

    # First Pass: Collect labels and their addresses
    current_address = None
    for line in assembly_code:
        parts = line.split()
        if parts[0] == "START":
            current_address = int(parts[1])
        elif parts[0] != "END":
            if(len(parts)==2):
              if(parts[0]=="READ"):
                symbol_table[parts[1]]="Not assigned"
            elif(len(parts)==4):
              symbol_table[parts[0]]=current_address
            elif(len(parts)==3 and parts[1]=="DS"):
              symbol_table[parts[0]]=current_address
              current_address+=int(parts[2])-1
            elif(len(parts)==3 and parts[1]=="DC"):
              symbol_table[parts[0]]=current_address
            current_address+=1


    # Second Pass: Generate Symbol Table
    print("Symbol Table:")
    print("Label\t|\tAddress")
    print("----------------------")
    for label, address in symbol_table.items():
        # if label=="READ":
        #   continue
        print(f"{label}\t|\t{address}")

# Assembly code
assembly_code = [
    "START 100",
    "READ A",
    "READ B",
    "LOOP MOVER AREG, A",
    "MOVER BREG, B",
    "COMP BREG, ='2'",
    "BC GT, LOOP",
    "BACK SUB AREG, B",
    "COMP AREG, ='5'",
    "BC LT, BACK",
    "STOP",
    "A DS 1",
    "print suyash",
    "B DS 1",
    "END"
]

# Generate symbol table
generate_symbol_table(assembly_code)