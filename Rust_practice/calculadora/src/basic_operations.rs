pub struct BasicOperation {
    num1: f32,
    num2: f32,
}

impl BasicOperation{
    pub fn new(num1: f32, num2: f32) -> BasicOperation {
        BasicOperation {
            num1,
            num2,
        }
    }
    pub fn add(&self) -> f32 {
        self.num1 + self.num2
    }
    pub fn sub(&self) -> f32 {
        self.num1 - self.num2
    }
    pub fn mul(&self) -> f32 {
        self.num1 * self.num2
    }
    pub fn div(&self) -> Option<f32> {
        if self.num2 == 0.0 {
            None
        } else {
            Some(self.num1 / self.num2)
        }
    }
}