import os
import subprocess


def get_text_diff(directory):
    result = subprocess.run(['diff', f'data/{directory}/original.txt', f'data/{directory}/new.txt'],
                            stdout=subprocess.PIPE)
    return result.stdout.decode('utf-8')


def generate_file_diff(text, directory):
    with open(f'data/{directory}/diff.txt', 'w') as file:
        file.write(text)


def main():
    directories = os.listdir("data/")
    for directory in directories:
        text_diff = get_text_diff(directory)
        generate_file_diff(text_diff, directory)


if __name__ == "__main__":
    main()
