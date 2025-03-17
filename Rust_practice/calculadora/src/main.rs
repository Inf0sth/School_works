use std::io;
mod basic_operations;

fn main() {
    let mut input = String::new();

    println!("\nEnter first value => ");
    io::stdin().read_line(&mut input).expect("Input error");
    let num1: f32 = match input.trim().parse() {
        Ok(n) => n,
        Err(_) => {
            println!("Invalid number!");
            return;
        }
    };
    input.clear();

    println!("\nEnter second value => ");
    io::stdin().read_line(&mut input).expect("Input error");
    let num2: f32 = match input.trim().parse() {
        Ok(n) => n,
        Err(_) => {
            println!("Invalid number!");
            return;
        }
    };
    input.clear();

    let operation = basic_operations::BasicOperation::new(num1, num2);
    
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

    match option {
        1 => println!("Result: {}", operation.add()),
        2 => println!("Result: {}", operation.sub()),
        3 => println!("Result: {}", operation.mul()),
        4 => match operation.div() {
            Some(result) => println!("Result: {}", result),
            None => println!("Error: Division by zero is not allowed!"),
        },
        _ => println!("Invalid option!"),
    };
    
}