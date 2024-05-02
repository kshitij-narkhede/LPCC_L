assembly_code = [
    "LOAD A",
    "STORE B",
    "MACRO ABC",
    "LOAD p",
    "SUB q",
    "MEND",
    "MACRO ADD1 ARG",
    "LOAD X",
    "STORE ARG",
    "MEND",
    "MACRO ADD5 A1, A2, A3",
    "STORE A2",
    "ADD1 5",
    "ADD1 10",
    "LOAD A1",
    "LOAD A3",
    "MEND",
    "ABC",
    "ADD5 D1, D2, D3",
    "END",
]

macro_definition_table = []
macro_name_table = {}

current_macro_name = None
current_macro_start = None

for line in assembly_code:
    parts = line.split()
    if len(parts) == 0:
        continue

    if parts[0] == "MACRO":
        current_macro_name = parts[1]
        current_macro_start = len(macro_definition_table)
    elif parts[0] == "MEND":
  
        if current_macro_name:

            macro_name_table[current_macro_name] = current_macro_start
            current_macro_name = None
            current_macro_start = None
    else:
        if current_macro_name:

            macro_definition_table.append(line)

print("Macro Name Table (MNT):")
print("Macro Name\tMDT Index")
for macro_name, mdt_index in macro_name_table.items():
    print(f"{macro_name}\t\t{mdt_index}")

print("\nMacro Definition Table (MDT):")
print("MDT Index\tInstruction")
for index, instruction in enumerate(macro_definition_table):
    print(f"{index}\t\t{instruction}")
