use std::io;

fn main() {
    let mut input = String::new();
    println!("\nEnter first value => ");
    io::stdin().read_line(&mut input).expect("Input error");
    let num1: i64 = match input.trim().parse() {
        Ok(n) => n,
        Err(_) => {
            println!("Invalid number!");
            return;
        }
    };
    input.clear();
    println!("\nEnter second value => ");
    io::stdin().read_line(&mut input).expect("Input error");
    let num2: i64 = match input.trim().parse() {
        Ok(n) => n,
        Err(_) => {
            println!("Invalid number!");
            return;
        }
    };
    input.clear();

    println!(
"\nChoice operation:
1) Add
2) Subtract
3) Multiply
4) Divide\n>> "
    );
    io::stdin().read_line(&mut input).expect("Input error");
    let option: u8 = match input.trim().parse() {
        Ok(n) => n,
        Err(_) => {
            println!("Invalid option!");
            return;
        }
    };
    let result: i64 = match option {
        1 => add(num1, num2),
        2 => sub(num1, num2),
        3 => mul(num1, num2),
        4 => {
            if num2 == 0 {
                println!("Error: Division by zero is not allowed!");
                return;
            }
            div(num1, num2)
        }
        _ => {
            println!("Invalid option!");
            return;
        }
    };
    println!("Result: {}", result);
}

fn add(a: i64, b: i64) -> i64 {a + b}
fn sub(a: i64, b: i64) -> i64 {a - b}
fn mul(a: i64, b: i64) -> i64 {a * b}
fn div(a: i64, b: i64) -> i64 {a / b}
